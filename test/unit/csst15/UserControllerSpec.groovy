package csst15

import csst15.command.PasswordChangeCommand
import csst15.conf.FieldVisibilityConf
import csst15.security.User
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(UserController)
@Build([User, FieldVisibilityConf])
class UserControllerSpec extends Specification {
    void "test the manipulateFieldVisibility action"() {
        setup:
        params.fieldName = "isUsernameVisible"
        request.method = 'POST'
        def id = params.userId = 1
        def user = User.build(id: id)
        def visFieldConfig = FieldVisibilityConf.build(user: user, isUsernameVisible: false)
        User.metaClass.'static'.findById = { userId -> user }
        FieldVisibilityConf.metaClass.'static'.findByUser = { newUser -> visFieldConfig }

        when:
        controller.manipulateFieldVisibility()

        then:
        visFieldConfig.isUsernameVisible
    }

    void "test change password when user not found"() {
        setup:
        def passwordCmd = new PasswordChangeCommand(oldPassword: '123456', newPassword: 'qwerty', confirmPassword: 'qwerty')
        controller.springSecurityService = [currentUser: null]

        when:
        controller.changePassword(passwordCmd)

        then:
        response.redirectUrl == '/home'
    }

    void "test change password when command object has errors"() {
        setup:
        def passwordCmd = new PasswordChangeCommand(oldPassword: '123456', newPassword: '1', confirmPassword: 'werty')
        def user = User.build()
        controller.springSecurityService = [currentUser: user]

        when:
        controller.changePassword(passwordCmd)

        then:
        controller.modelAndView.viewName == '/user/changePassword'
    }

    void "test profile action when user not found"() {
        setup:
        controller.springSecurityService = [currentUser: null]
        User.metaClass.'static'.findByUsername = { username -> null }

        when:
        controller.profile()

        then:
        response.redirectedUrl == "/not-found"
    }
}
