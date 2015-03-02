package csst15

import csst15.command.QuickNewUserCommand
import csst15.security.User
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import grails.util.Holders
import groovy.util.logging.Slf4j

@Slf4j
@Transactional(readOnly = true)
@Secured('permitAll')
class AuthController {
    def springSecurityService
    def notificationService
    def userService

    static allowedMethods = [signup: 'GET', forgot_password: 'GET', register: 'POST']

    def signup() {
        render(view: 'signup')
    }

    def forgot_password() {
        render(view: 'forgot-password')
    }

    @Transactional
    def register(QuickNewUserCommand userCommand) {
        if (userCommand.hasErrors())
            render(view: 'signup', model: [userInstance: userCommand])
        else {
            def user = userService.createQuickUser(userCommand)

            if (user?.id) {
                if (Holders.config.grails.email.skip) {
                    log.debug("Skip email confirmation step. Active user")
                    user.enabled = true
                } else {
                    user.activationToken = UUID.randomUUID().toString()
                    def activationLink = createLink(absolute: true, controller: 'auth', action: 'activateuser', params: [activationToken: user.activationToken])
                    notificationService.sendActivationEmail(user, activationLink)
                }
                user.save()
                redirect(controller: 'home')
            } else {
                log.error("Failed to register new user account")
                render(view: 'signup', model: [userInstance: userCommand])
            }
        }
    }

    @Transactional
    def sendResetPasswordEmail() {
        def user = User.findByEmail(params.email)
        user.passwordResetToken = UUID.randomUUID()
        user.save(failOnError: true)
        if (user) {
            try {
                def passwordResetLink = createLink(absolute: true, action: 'passwordresetpage', params: [token: user.passwordResetToken])
                notificationService.sendResetPasswordEmail(user, passwordResetLink)
                flash.message = "login-page.flash-message.reset-password-mail-sent.text"
                flash.args = [user.email]
                redirect(controller: 'login', action: 'auth')
            } catch (Exception e) {
                log.error("Could not send the password reset email. Error : ${e.message}", e)
                flash.error = 'reset-password-page.initiate-reset-password-form.mail-send-error.label'
                flash.args = [e.message]
                render view: 'forgot-password'
            }

        }
    }

    @Transactional
    def activateuser() {
        def user = User.findByActivationToken(params.activationToken)
        if (user) {
            user.activationToken = UUID.randomUUID()
            user.enabled = true
            user.save()

            flash.message = "login-page.flash-message.account-activation-successful.text"
        } else
            flash.error = "login-page.flash-message.invalid-account-activation-link.text"

        redirect(controller: 'login', action: 'auth')
    }

    def passwordresetpage() {
        def user = null

        if (params.token)
            user = User.findByPasswordResetToken(params.token)
        else if (springSecurityService.isLoggedIn())
            user = User.findById(springSecurityService.principal.id as Long)

        if (user == null) {
            flash.error = "login-page.flash-message.invalid-password-reset-link.text"
            redirect(controller: 'login', action: 'auth')
            return
        }
        [passwordResetToken: params.token]
    }

    @Transactional
    def resetpassword() {
        def passwordMatch = params.password == params.rpassword
        if (passwordMatch) {
            User user = null
            if (params.passwordResetToken && !params.passwordResetToken.empty) {
                user = User.findByPasswordResetToken(params.passwordResetToken)
            } else if (springSecurityService.isLoggedIn()) {
                user = User.findById(springSecurityService.principal.id as Long)
            }

            if (user) {
                User.withTransaction { status ->
                    try {
                        user.password = params.password
                        user.passwordResetToken = null
                        user.save(failOnError: true)
                        log.debug("Password reset of the user '${user.username}' was successfull.")
                        flash.message = "login-page.flash-message.password-reset-successful.text"
                        redirect(controller: 'login', action: 'auth')
                    } catch (Exception e) {
                        status.setRollbackOnly()
                        log.error("Password reset for the user '${user.username} was unsuccessfull. Error : ${e.message}", e)
                        flash.error = "reset-password-page.reset-password-form.exception-occurred.message.text"
                        flash.args = [e.message]
                        render view: 'passwordresetpage', model: [passwordResetToken: params.passwordResetToken]
                    }
                }
            } else {
                flash.error = "login-page.flash-message.invalid-password-reset-link.text"
                redirect(controller: 'login', action: 'auth')
            }
        } else {
            flash.error = "reset-password-page.reset-password-form.password-mismatch.message.text"
            redirect(action: 'passwordresetpage', params: [token: params.passwordResetToken])
        }
    }
}
