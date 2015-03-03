package csst15

import csst15.security.Role
import csst15.security.User
import csst15.security.UserRole
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(HomeController)
@Build([User, Role])
class HomeControllerSpec extends Specification {
    void "test index action"() {
        when:
        controller.index()

        then:
        controller.modelAndView.viewName == '/index'
    }

    void "test the about action"() {
        when:
        controller.about()

        then:
        controller.modelAndView.viewName == '/about'
    }

    void "test the list action"() {
        setup:
        def user1 = User.build()
        def user2 = User.build()
        UserRole.metaClass.'static'.findAllByRole = { userRole -> [user: [user1, user2]] }

        when:
        controller.list()

        then:
        controller.modelAndView.viewName == '/list'
        controller.modelAndView.model.users == [user1, user2]
    }
}
