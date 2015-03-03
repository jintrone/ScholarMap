package csst15

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
}
