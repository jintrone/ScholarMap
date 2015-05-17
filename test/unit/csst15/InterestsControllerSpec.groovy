package csst15

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(InterestsController)
class InterestsControllerSpec extends Specification {
    void "test addInterest with invalid user"() {
        setup:
        request.method = "POST"
        controller.springSecurityService = [currentUser: null]

        when:
        controller.addInterest()

        then:
        response.redirectedUrl == "/login/auth"
    }
}
