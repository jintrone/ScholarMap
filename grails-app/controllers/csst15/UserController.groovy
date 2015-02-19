package csst15

import csst15.security.User
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import grails.validation.ValidationException
import groovy.util.logging.Slf4j

@Slf4j
@Transactional(readOnly = true)
@Secured(['IS_AUTHENTICATED_FULLY'])
class UserController {
    def springSecurityService
    def uploadService

    static allowedMethods = [update: 'POST']

    def profile(User user) {
        [user: user]
    }

    def edit(User user) {
        if (!user) {
            response.sendError(403)
            return
        }

        [user: user]
    }

    @Transactional
    def update() {
        User user = User.findById(springSecurityService.principal.id as Long)

        if (!user) {
            return response.sendError(403)
        }
        if (params.photo) {
            params.photo = uploadService.uploadFile(request, 'photo')
        }
        bindData(user, params)
        User.withTransaction { status ->
            try {
                user.save(failOnError: true)
                log.info("Updated user ${user.email} successfully with id ${user.id}.")
                render(view: 'profile', model: [user: user])
            } catch (e) {
                status.setRollbackOnly()
                log.error("Could not update the user with username ${user.email}")
                if (!(e instanceof ValidationException))
                    flash.error = e.message
                render(view: 'edit', model: [user: user, exception: e])
            }
        }
    }
}

