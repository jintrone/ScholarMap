package csst15

import csst15.security.User
import grails.transaction.Transactional
import groovy.util.logging.Slf4j

@Slf4j
@Transactional
class NotificationService {
    def mailService
    def groovyPageRenderer
    def mailSender
    def messageSource

    def sendActivationEmail(User user, def activationLink) {
        try {
            log.info("Sending activation email to '${user.email}'.")
            mailService.sendMail {
                async true
                to user.email
                from mailSender.username
                subject messageSource.getMessage('user-registration.notification.subject.text', [user.firstName + " " + user.lastName] as Object[], Locale.US)
                html groovyPageRenderer.render(template: '/notificationTemplates/userActivationEmail',
                        model: [name: user.firstName + " " + user.lastName, activationLink: activationLink])
            }
            log.info("The activation email to '${user.email}' was sent.")
        } catch (Exception e) {
            log.error("Could not send the activation email. Exception : ${e.message}", e)
            throw new Exception(e.message)
        }
    }

    def sendResetPasswordEmail(User user, def passwordResetLink) {
        log.info("Sending password reset email to the user '${user.email}'.")
        mailService.sendMail {
            async true
            to user.email
            from mailSender.username
            subject messageSource.getMessage('password-reset.notification.subject.text', null, Locale.US)
            html groovyPageRenderer.render(template: '/notificationTemplates/passwordResetEmail',
                    model: [name: "${user.firstName} ${user.lastName}", passwordResetLink: passwordResetLink])
        }
        log.info("Sent password reset email to the user '${user.email}'.")
    }
}
