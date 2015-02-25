package csst15

import csst15.conf.FieldLockConf
import csst15.conf.GeneralConf
import csst15.constants.Roles
import csst15.security.Role
import csst15.security.User
import csst15.security.UserRole
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.util.logging.Slf4j

@Slf4j
@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN'])
class AdminController {
    static allowedMethods = [
            deleteUser         : 'DELETE',
            manipulateReg      : 'GET',
            manipulateFieldLock: 'POST',
            board              : 'GET'
    ]

    def board() {
        def users = UserRole.findAllByRole(Role.findByAuthority(Roles.USER.name)).user
        [users: users]
    }

    @Transactional
    def deleteUser(int userId) {
        log.info("Deleting user with id ${userId}")
        def user = User.findById(userId)
        user.delete()
        log.info("Deleted user with id ${userId}")
    }

    @Transactional
    def manipulateReg() {
        def generalConf = GeneralConf.findById(1)
        generalConf.isRegEnabled = !generalConf.isRegEnabled
        generalConf.save(failOnError: true)
        log.info("User registration ability is set to ${generalConf.isRegEnabled}")
    }

    @Transactional
    def manipulateFieldLock() {
        def fieldName = params.fieldName
        def user = User.findById(params.userId)
        def fieldLockConfig = FieldLockConf.findByUser(user)
        if (fieldName && fieldLockConfig) {
            def field = GeneralUtils.constructFieldForLock(fieldName)
            fieldLockConfig."${field}" = !fieldLockConfig."${field}"
            fieldLockConfig.save(failOnError: true)
            log.info("'${fieldName}' field is enabled ${fieldLockConfig."${field}"}")
        } else {
            log.debug("Field lock config not found for '${user.email}' with id ${user.id}.")
        }
    }
}
