package csst15.lists

import csst15.constants.Departments
import csst15.utils.ConstraintsUnitSpec
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Unroll

@TestFor(Department)
@Build(Department)
class DepartmentSpec extends ConstraintsUnitSpec {

    def setup() {
        mockForConstraintsTests(Department, [Department.build(title: Departments.MEDIA.title)])
    }

    @Unroll("Tested Department field #field with the value #val")
    def "test Department fields' constraints"() {
        when:
        def obj = new Department("$field": val)

        then:
        validateConstraints(obj, field, target)

        where:
        target     | field   | val
        'nullable' | 'title' | null
        'nullable' | 'title' | getEmptyString()
        'unique'   | 'title' | Departments.MEDIA.title
        'valid'    | 'title' | Departments.ADVERTISING.title
    }
}
