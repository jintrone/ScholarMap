package csst15.security

import csst15.utils.ConstraintsUnitSpec
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import org.joda.time.LocalDate
import spock.lang.Unroll

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
        target     | field            | val
        'nullable' | 'username'       | null
        'nullable' | 'username'       | getEmptyString()
        'valid'    | 'username'       | 'user'
        'nullable' | 'password'       | getEmptyString()
        'valid'    | 'password'       | '1'
        'valid'    | 'firstName'      | 'Admin'
        'valid'    | 'firstName'      | null
        'valid'    | 'lastName'       | null
        'valid'    | 'lastName'       | 'Admin'
        'valid'    | 'degreeYear'     | LocalDate.now().year
        'max'      | 'degreeYear'     | LocalDate.now().plusYears(1).year
        'valid'    | 'institution'    | null
        'email'    | 'email'          | getEmailText(false)
        'nullable' | 'email'          | null
        'nullable' | 'email'          | getEmptyString()
        'unique'   | 'email'          | 'test@test.com'
        'valid'    | 'email'          | getEmailText(true)
        'valid'    | 'photo'          | null
        'valid'    | 'specialization' | null
        'valid'    | 'role'           | null
        'valid'    | 'department'     | null
    }
}
