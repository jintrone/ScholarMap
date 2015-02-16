package csst15

import csst15.security.User
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(AuthController)
@Mock(User)
class AuthControllerSpec extends Specification {
    void "test signup action"() {
        when:
        controller.signup()

        then:
        controller.modelAndView.viewName == '/auth/signup'
    }

    void "test forgot password action"() {
        when:
        controller.forgot_password()

        then:
        controller.modelAndView.viewName == '/auth/forgot-password'
    }
}
