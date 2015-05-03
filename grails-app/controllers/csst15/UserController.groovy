package csst15

import csst15.command.PasswordChangeCommand
import csst15.command.RequiredFieldsCommand
import csst15.command.UpdateUserCommand
import csst15.conf.FieldLockConf
import csst15.conf.FieldMandatoryConf
import csst15.conf.FieldVisibilityConf
import csst15.constants.Roles
import csst15.lists.Department
import csst15.lists.Position
import csst15.lists.Specialization
import csst15.security.User
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import grails.validation.ValidationException
import groovy.util.logging.Slf4j
import org.springframework.http.HttpStatus

@Slf4j
@Transactional(readOnly = true)
class UserController {
    static allowedMethods = [
            manipulateFieldVisibility: 'POST',
            changePasswordPage  : 'GET',
            update              : 'POST',
            fillRequiredFields  : 'GET',
            updateRequiredFields: 'POST',
    ]
    def springSecurityService
    def uploadService
    def userService

    def profile() {
        def currentUser = springSecurityService.currentUser as User
        def user = User.findByUsername(params.username)
        if (user) {
            def entities = UserEntity.findAllByUser(user)?.entity
            if (currentUser) {
                [user: user, hasCurrentUser: true, entities: entities]
            } else {
                [user: user]
            }

        } else {
            redirect(uri: '/not-found')
        }
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def edit() {
        def user = User.findByUsername(params.username)
        if (user) {
            if (user.id == springSecurityService.principal.id) {
                def fieldsLockConf = FieldLockConf.findByUser(user)
                def fieldsVisConf = FieldVisibilityConf.findByUser(user)
                [user: user, lockConf: fieldsLockConf, visConf: fieldsVisConf]
            } else {
                redirect(uri: '/access-denied')
            }
        } else {
            redirect(controller: 'admin')
        }
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def fillRequiredFields() {
        def user = springSecurityService.currentUser as User
        if (user) {
            if (user.id == springSecurityService.principal.id) {
                def mandatoryFields = FieldMandatoryConf.findAllByIsMandatory(true).fieldName
                [user: user, mandatoryFields: mandatoryFields]
            } else {
                redirect(uri: '/access-denied')
            }
        } else {
            redirect(controller: 'admin')
        }
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    @Transactional
    def updateRequiredFields(RequiredFieldsCommand command) {
        if (command.hasErrors()) {
            redirect(action: 'fillRequiredFields')
        } else {
            if (springSecurityService.principal.id.toString() == params.userId?.toString()) {
                def user = userService.updateProfile(command, springSecurityService.currentUser as User)

                if (user) {
                    redirect(controller: 'home')
                } else {
                    redirect(action: 'fillRequiredFields')
                }
            } else {
                redirect(uri: '/access-denied')
            }
        }
    }

    @Transactional
    @Secured(['IS_AUTHENTICATED_FULLY'])
    def update(UpdateUserCommand userCommand) {
        User currentUser = springSecurityService.currentUser as User
        User user = null

        if (currentUser.authorities.any { it.authority == Roles.ADMIN.name }) {
            user = User.findById(params.userId)
        } else {
            user = currentUser
        }

        if (!user) {
            redirect(controller: 'login', action: 'auth')
        }

        userCommand.position = Position.findByName(params.position)
        userCommand.specialization = Specialization.findByTitle(params.specialization)
        userCommand.department = Department.findByTitle(params.department)
        if (userCommand.hasErrors()) {
            def fieldsLockConf = FieldLockConf.findByUser(user)
            def fieldsVisConf = FieldVisibilityConf.findByUser(user)
            render(view: 'edit', model: [user: user, userCommand: userCommand, visConf: fieldsVisConf, lockConf: fieldsLockConf])
        } else {
            if (userCommand.photo) {
                userCommand.photo = uploadService.uploadFile(request, 'photo')
            }
            bindData(user, params, [exclude: ['position', 'specialization', 'department']])
            user.position = Position.findByName(params.position)
            user.specialization = Specialization.findByTitle(params.specialization)
            user.department = Department.findByTitle(params.department)

            User.withTransaction { status ->
                try {
                    user.save(failOnError: true)
                    log.info("Updated user ${user.email} successfully with id ${user.id}.")
                    redirect(action: 'profile', params: [username: user.username])
                } catch (e) {
                    status.setRollbackOnly()
                    log.error("Could not update the user with username ${user.email}")
                    if (!(e instanceof ValidationException))
                        flash.error = e.message
                    def fieldsLockConf = FieldLockConf.findByUser(user)
                    render(view: 'edit', model: [user: user, lockConf: fieldsLockConf, exception: e])
                }
            }
        }
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def changePasswordPage() {
        def user = User.findByUsername(params.username)
        if (user) {
            if (user.id == springSecurityService.principal.id) {
                render(view: 'changePassword', model: [user: user])
            }
        }
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    @Transactional
    def changePassword(PasswordChangeCommand command) {
        def user = springSecurityService.currentUser as User

        if (user) {
            if (command.validate()) {
                user.password = command.newPassword
                user.save(failOnError: true)
                log.debug("Password change of the user '${user.username}' was successfull.")
                redirect(action: 'profile', params: [username: user.username])
            } else {
                render(view: 'changePassword', model: [command: command, user: user])
            }
        } else {
            redirect(controller: 'home')
        }
    }

    @Transactional
    @Secured(['IS_AUTHENTICATED_FULLY'])
    def manipulateFieldVisibility() {
        def user = User.findById(params.userId)
        if (params.fieldName) {
            def fieldName = params.fieldName
            def fieldVisibilityConfig = FieldVisibilityConf.findByUser(user)
            if (fieldName && fieldVisibilityConfig) {
                fieldVisibilityConfig."${fieldName}" = !fieldVisibilityConfig."${fieldName}"
                fieldVisibilityConfig.save(failOnError: true)
                log.info("'${fieldName}' field is public ${fieldVisibilityConfig."${fieldName}"} for the user with id ${user.id}")
                render status: HttpStatus.OK
            } else {
                log.debug("fieldName not specified.")
                redirect(action: 'profile', params: [username: user?.username])
            }
        } else {
            log.debug("fieldName not specified.")
            redirect(action: 'profile', params: [username: user.username])
        }
    }

    def avatar_image() {
        def user = User.get(params.id)
        if (!user || !user.photo) {
            response.sendError(404)
            return
        }
        response.contentType = "png"
        response.contentLength = user.photo.size()
        OutputStream out = response.outputStream
        out.write(user.photo)
        out.close()
    }
}

