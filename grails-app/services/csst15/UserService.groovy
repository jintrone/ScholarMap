package csst15

import csst15.command.LoadUserCommand
import csst15.command.QuickNewUserCommand
import csst15.command.RequiredFieldsCommand
import csst15.conf.FieldLockConf
import csst15.conf.FieldVisibilityConf
import csst15.constants.Roles
import csst15.lists.Department
import csst15.lists.Position
import csst15.lists.Specialization
import csst15.security.Role
import csst15.security.User
import csst15.security.UserRole
import grails.transaction.Transactional
import groovy.util.logging.Slf4j

@Slf4j
@Transactional
class UserService {
    def notificationService
    def springSecurityService

    def createQuickUser(QuickNewUserCommand userCommand) {
        def user = new User()
        user.properties = userCommand.properties
        addConfigToUser(user)

        if (user.save(flush: true)) {
            log.info("Created user with id ${user.id}")
            addDefaultRole(user)
            return user
        } else {
            log.error("User creation attempt failed")
            log.error(user.errors.dump())
        }

        return null
    }

    def createUser(String email) {
        createUser(email, constructUsername(email), makeRandomPassword())
    }

    def createUser(String email, String username, String password) {
        def user = new User(email: email, username: username, password: password, enabled: true)
        addConfigToUser(user)

        if (user.save(flush: true)) {
            log.info("Created user with id ${user.id}")
            addDefaultRole(user)
            notificationService.sendInvitationToUser(user, password)
            return user
        } else {
            log.error("User creation attempt failed")
            log.error(user?.errors?.dump())
        }

        return null
    }

    def createUser(Map fieldsMap) {
        for (int i = 1; i <= fieldsMap.size(); i++) {
            LoadUserCommand loadUserCommand = new LoadUserCommand()
            List fields = fieldsMap.get(i)

            if (fields?.size() > 0) {
                def user = new User()
                loadUserCommand.username = fields?.get(0)
                loadUserCommand.email = fields?.get(1)
                loadUserCommand.firstName = fields?.get(2)
                loadUserCommand.lastName = fields?.get(3)
                loadUserCommand.institution = fields?.get(4)
                loadUserCommand.degreeYear = fields?.get(5)
                loadUserCommand.position = fields?.get(6)
                loadUserCommand.department = fields?.get(7)
                loadUserCommand.specialization = fields?.get(8)

                if (loadUserCommand.validate()) {
                    user.username = loadUserCommand.username
                    user.email = loadUserCommand.email
                    user.firstName = loadUserCommand.firstName
                    user.lastName = loadUserCommand.lastName
                    user.degreeYear = loadUserCommand.degreeYear
                    def password = makeRandomPassword()
                    user.password = password
                    user.enabled = true
                    user.institution = loadUserCommand.institution
                    user.department = loadUserCommand.department ? Department.findByTitle(loadUserCommand.department) : user.department
                    user.position = loadUserCommand.position ? Position.findByName(loadUserCommand.position) : user.position
                    user.specialization = loadUserCommand.specialization ? Specialization.findByTitle(loadUserCommand.specialization) : user.specialization
                    user.lockConf = new FieldLockConf()
                    user.visibilityConf = new FieldVisibilityConf()

                    if (user.save()) {
                        log.info("Created user with username: ${user.username}, id: ${user.id}")
                        addDefaultRole(user)
                        notificationService.sendInvitationToUser(user, password)

                    } else {
                        log.error("User creation attempt failed")
                        log.error(user?.errors?.dump())
                    }
                }
            }

        }

        return true
    }

    def updateProfile(RequiredFieldsCommand command, User user) {
        user.firstName = command.firstName ? command.firstName : user.firstName
        user.lastName = command.lastName ? command.lastName : user.lastName
        user.degreeYear = command.degreeYear ? command.degreeYear : user.degreeYear
        user.institution = command.institution ? command.institution : user.institution
        user.department = command.department ? Department.findByTitle(command.department) : user.department
        user.position = command.position ? Position.findByName(command.position) : user.position
        user.specialization = command.specialization ? Specialization.findByTitle(command.specialization) : user.specialization

        if (user.save(flush: true)) {
            log.info("Updated user with id ${user.id}")
            return user
        } else {
            log.error("User creation attempt failed")
            log.error(user.errors.dump())
        }

        return null
    }


    private void addDefaultRole(User user) {
        def role = Role.findByAuthority(Roles.USER.name)
        UserRole.create(user, role)
    }


    private String makeRandomPassword() {
        return "${System.currentTimeMillis()}"
    }

    private String constructUsername(String email) {
        return email.split("@")[0]
    }

    private void addConfigToUser(User user) {
        user.lockConf = new FieldLockConf()
        user.visibilityConf = new FieldVisibilityConf()
    }
}
