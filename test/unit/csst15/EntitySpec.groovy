package csst15

import csst15.utils.ConstraintsUnitSpec
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(Entity)
@Build(Entity)
class EntitySpec extends ConstraintsUnitSpec {

    def setup() {
        mockForConstraintsTests(Entity, [Entity.build(name: 'name')])
    }

    @Unroll("Tested Entity field #field with the value #val")
    def "test Entity fields' constraints"() {
        when:
        def obj = new Entity("$field": val)

        then:
        validateConstraints(obj, field, target)

        where:
        target     | field         | val
        'nullable' | 'name'        | null
        'nullable' | 'name'        | getEmptyString()
        'valid'    | 'name'        | getCustomTextWithLength(5)
        'valid'    | 'description' | null
    }
}
