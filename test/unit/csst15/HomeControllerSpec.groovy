package csst15

import csst15.security.Role
import csst15.security.User
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(HomeController)
@Build([User, Role, Entity, Reference])
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

//    void "test the list action"() {
//        setup:
//        def user1 = User.build()
//        def user2 = User.build()
//        UserRole.metaClass.'static'.findAllByRole = { userRole -> [user: [user1, user2]] }
//
//        when:
//        controller.list()
//
//        then:
//        controller.modelAndView.viewName == '/list'
//        controller.modelAndView.model.users == [user1, user2]
//    }

    void "test areas action: method GET"() {
        setup:
        request.method = "GET"

        when:
        controller.areas()

        then:
        controller.modelAndView.viewName == '/home/areas'
    }

//    void "test areas action: method POST"() {
//        setup:
//        request.method = "POST"
//        params.length = "10"
//        params.start = "0"
//        params."order[0][dir]" = "asc"
//        def area = Entity.build(type: EntityType.FIELD, name: 'new area', description: 'new area')
////        def myCriteria = new Entity();
////        myCriteria.get = { Closure cls -> area }
//        Entity.metaClass.'static'.createCriteria = { area }
//
//        when:
//        controller.areas()
//
//        then:
//        println "================"
//        println controller.response.text
//        println "================"
//        controller.response.text.contains('new area')
//    }

    void "test methods action: method GET"() {
        setup:
        request.method = "GET"

        when:
        controller.methods()

        then:
        controller.modelAndView.viewName == '/home/methods'
    }

    void "test theories action: method GET"() {
        setup:
        request.method = "GET"

        when:
        controller.theories()

        then:
        controller.modelAndView.viewName == '/home/theories'
    }

    void "test venues action: method GET"() {
        setup:
        request.method = "GET"

        when:
        controller.venues()

        then:
        controller.modelAndView.viewName == '/home/venues'
    }

//    void "test method references"() {
//        given:
//        request.method = "POST"
//        params.length = "10"
//        params."order[0][dir]" = "asc"
//        params.start = 0
//        def ref1 = Reference.build()
//        def ref2 = Reference.build()
//        Reference.metaClass.'static'.list = { [ref1, ref2] }
//
//        expect:
//        controller.references() == [references: [ref1, ref2]]
//    }
}
