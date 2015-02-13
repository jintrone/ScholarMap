package csst15.security

import csst15.utils.ConstraintsUnitSpec
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@Build(User)
@TestFor(User)
class UserSpec extends ConstraintsUnitSpec {

    def setup() {
        mockForConstraintsTests(User, [User.build(username: 'admin', password: '1', email: 'test@test.com')])
    }

    @Unroll("Tested User field #field with the value #val")
    def "test User fields' constraints"() {
        when:
        def obj = new User("$field": val)

        then:
        validateConstraints(obj, field, target)

        where:
        target     | field       | val
        'nullable' | 'username'  | null
        'nullable' | 'username'  | getEmptyString()
        'valid'    | 'username'  | 'user'
        'valid'    | 'firstName' | 'Admin'
        'valid'    | 'firstName' | null
        'valid'    | 'lastName'  | null
        'valid'    | 'lastName'  | 'Admin'
    }
}
