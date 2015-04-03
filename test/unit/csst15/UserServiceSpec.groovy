package csst15

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(UserService)
class UserServiceSpec extends Specification {
    void "test constructUsername method"() {
        expect:
        service.constructUsername("emil.matev@gmail.com") == "emil.matev"
    }
}
