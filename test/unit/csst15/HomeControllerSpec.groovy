package csst15

import grails.test.mixin.TestFor
import spock.lang.Specification


@TestFor(HomeController)
class HomeControllerSpec extends Specification {
    void "test index action"() {
        when:
        controller.index()

        then:
        controller.modelAndView.viewName == '/home/index'
    }
}
