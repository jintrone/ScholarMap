package csst15

import csst15.conf.FieldLockConf
import csst15.conf.FieldMandatoryConf
import csst15.conf.FieldVisibilityConf
import csst15.conf.GeneralConf
import csst15.constants.Roles
import csst15.security.Role
import csst15.security.User
import csst15.security.UserRole
import grails.buildtestdata.mixin.Build
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(AdminController)
@Mock(User)
@Build([User, GeneralConf, FieldLockConf, Role, FieldMandatoryConf, FieldVisibilityConf])
class AdminControllerSpec extends Specification {
    void "test the board action"() {
        given:
        def user1 = User.build(id: 1)
        def user2 = User.build(id: 2)
        def generalConf = GeneralConf.build(isRegEnabled: true, id: 1)
        GeneralConf.metaClass.'static'.findById = { genConfId -> generalConf }
        def roleUser = Role.build(authority: Roles.USER.name)
        Role.metaClass.'static'.findByAuthority = { authority -> roleUser }
        UserRole.metaClass.'static'.findAllByRole = { role -> [user: [user1, user2], role: roleUser] }

        expect:
        controller.board().users.size() == 2
        controller.board().isRegEnabled
    }

    void "test the delete action"() {
        setup:
        int id = 1
        def user = User.build(id: id, username: 'testUser')
        UserRole.metaClass.'static'.findByUser = { userInstance -> user }

        when:
        controller.deleteUser(user)

        then:
        !User.exists(id)
    }

    void "test lock field action"() {
        setup:
        params.fieldName = "isUsernameLocked"
        request.method = 'POST'
        def id = params.userId = 1
        def user = User.build(id: id)
        def lockFieldConfig = FieldLockConf.build(user: user, isUsernameLocked: false)
        User.metaClass.'static'.findById = { userId -> user }
        FieldLockConf.metaClass.'static'.findByUser = { newUser -> lockFieldConfig }

        when:
        controller.manipulateFieldLock()

        then:
        lockFieldConfig.isUsernameLocked
    }

    void "test lock field action when fieldName not found"() {
        setup:
        request.method = 'POST'
        def id = params.userId = 1
        def user = User.build(id: id)
        def lockFieldConfig = FieldLockConf.build(user: user, isUsernameLocked: false)
        User.metaClass.'static'.findById = { userId -> user }
        FieldLockConf.metaClass.'static'.findByUser = { newUser -> lockFieldConfig }

        when:
        controller.manipulateFieldLock()

        then:
        response.redirectUrl.contains('/admin/editUserProfile')
    }

    void "test disable registration"() {
        setup:
        int id = 1
        request.method = 'POST'
        def generalConf = GeneralConf.build(isRegEnabled: true, id: id)
        GeneralConf.metaClass.'static'.findById = { genConfId -> generalConf }

        when:
        controller.manipulateReg()

        then:
        !generalConf.isRegEnabled
    }

    void "test enable registration"() {
        setup:
        int id = 1
        request.method = 'POST'
        def generalConf = GeneralConf.build(isRegEnabled: false, id: id)
        GeneralConf.metaClass.'static'.findById = { genConfId -> generalConf }

        when:
        controller.manipulateReg()

        then:
        generalConf.isRegEnabled
    }

    void "test the manipulateFieldMand action"() {
        setup:
        def fieldName = params.fieldName = 'firstName'
        request.method = 'POST'
        def fieldMandConf = FieldMandatoryConf.build(fieldName: fieldName, isMandatory: false)
        FieldMandatoryConf.metaClass.'static'.findByFieldName = { genConfId -> fieldMandConf }

        when:
        controller.manipulateFieldMand()

        then:
        fieldMandConf.isMandatory
    }
}
