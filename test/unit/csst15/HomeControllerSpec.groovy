package csst15

import csst15.security.User
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification


@TestFor(HomeController)
@Mock(User)
class HomeControllerSpec extends Specification {
    void "test index action"() {
        when:
        controller.index()

        then:
        controller.modelAndView.viewName == '/index'
    }
}
