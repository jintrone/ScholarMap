package csst15.security

import csst15.constants.Roles
import csst15.utils.ConstraintsUnitSpec
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(Role)
@Build(Role)
class RoleSpec extends ConstraintsUnitSpec {

    def setup() {
        mockForConstraintsTests(Role, [Role.build(authority: Roles.ADMIN.name)])
    }

    @Unroll("Tested Role field #field with the value #val")
    def "test Role fields' constraints"() {
        when:
        def obj = new Role("$field": val)

        then:
        validateConstraints(obj, field, target)

        where:
        target     | field       | val
        'nullable' | 'authority' | null
        'nullable' | 'authority' | getEmptyString()
        'unique'   | 'authority' | Roles.ADMIN.name
        'valid' | 'authority' | Roles.USER.name
    }
}
