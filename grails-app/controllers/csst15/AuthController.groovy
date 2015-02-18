package csst15

import csst15.constants.Roles
import csst15.security.Role
import csst15.security.User
import csst15.security.UserRole
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import grails.util.Holders
import grails.validation.ValidationException
import groovy.util.logging.Slf4j

@Slf4j
@Transactional(readOnly = true)
@Secured('permitAll')
class AuthController {
    def springSecurityService
    def mailService
    def mailSender
    def groovyPageRenderer

    static allowedMethods = [signup: 'GET', forgot_password: 'GET', register: 'POST']

    def signup() {
        render(view: '/auth/signup')
    }

    def forgot_password() {
        render(view: '/auth/forgot-password')
    }

    @Transactional
    def register() {
        def user = new User(params)
        def isRolledback = false

        boolean passwordsMatch = params.password == params.rpassword
        user.enabled = true
        user.activationToken = UUID.randomUUID().toString()
        User.withTransaction { status ->
            try {
                if (!passwordsMatch)
                    render(view: '/auth/signup')
                user.save(failOnError: true)
                log.info("Registered user ${user.email} successfully with id ${user.id}.")
                if (Holders.config.grails.email.skip) {
                    log.debug "skip email confirmation step. active user"
                } else {
                    sendactivationemail(user)
                }
            } catch (e) {
                status.setRollbackOnly()
                isRolledback = true
                log.error("Could not save the user with username ${user.username}")
                log.error("Details of user which has failed to register :\n${user.toString()} ")
                if (!(e instanceof ValidationException))
                    flash.error = e.message
                render(view: '/auth/signup', model: [userInstance: user, exception: e])
            }

            if (!isRolledback) {
                UserRole.create(user, Role.findByAuthority(Roles.USER.name), true)
                springSecurityService.reauthenticate(user.username)
                redirect(controller: 'home')
            }
        }
    }

    def sendactivationemail(User user) {
        def activationLink = createLink(absolute: true, controller: 'auth', action: 'activateuser', params: [activationToken: user.activationToken])
        try {
            mailService.sendMail {
                to user.email
                from mailSender.username
                subject message(code: 'user-registration.notification.subject.text', args: [user.firstName + " " + user.lastName])
                html groovyPageRenderer.render(template: '/notificationTemplates/userActivationEmail', model: [name: user.firstName + " " + user.lastName, activationLink: activationLink])
            }
        } catch (Exception e) {
            log.error("Could not send the activation email. Exception : ${e.message}", e)
            throw new Exception(e.message)
        }
    }

    @Transactional
    def sendresetpasswordemail() {
        def user = User.findByEmail(params.email)
        if (user) {
            try {
                user.passwordResetToken = UUID.randomUUID()
                user.save(failOnError: true)
                def passwordResetLink = createLink(absolute: true, action: 'passwordresetpage', params: [token: user.passwordResetToken])
                mailService.sendMail {
                    to user.email
                    from mailSender.username
                    subject message(code: 'password-reset.notification.subject.text')
                    html groovyPageRenderer.render(template: '/notificationTemplates/passwordResetEmail', model: [name: "${user.firstName} ${user.lastName}", passwordResetLink: passwordResetLink])
                }

                log.info("Sent password reset email to the user '${user.username}'.")
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
