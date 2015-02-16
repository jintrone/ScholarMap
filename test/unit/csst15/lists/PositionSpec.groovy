package csst15.lists

import csst15.constants.Positions
import csst15.utils.ConstraintsUnitSpec
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Unroll

@TestFor(Position)
@Build(Position)
class PositionSpec extends ConstraintsUnitSpec {
    def setup() {
        mockForConstraintsTests(Position, [Position.build(name: Positions.PROFESSOR.name)])
    }

    @Unroll("Tested Position field #field with the value #val")
    def "test Position fields' constraints"() {
        when:
        def obj = new Position("$field": val)

        then:
        validateConstraints(obj, field, target)

        where:
        target     | field  | val
        'nullable' | 'name' | null
        'nullable' | 'name' | getEmptyString()
        'unique'   | 'name' | Positions.PROFESSOR.name
        'valid'    | 'name' | Positions.ASSISTANT_PROFESSOR.name
    }
}
