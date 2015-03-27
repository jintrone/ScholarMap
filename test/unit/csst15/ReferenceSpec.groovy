package csst15

import csst15.utils.ConstraintsUnitSpec
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Unroll

@TestFor(Reference)
@Build(Reference)
class ReferenceSpec extends ConstraintsUnitSpec {
    def setup() {
        mockForConstraintsTests(Reference, [Reference.build()])
    }

    @Unroll("Tested Reference field #field with the value #val")
    def "test Reference fields' constraints"() {
        when:
        def obj = new Reference("$field": val)

        then:
        validateConstraints(obj, field, target)

        where:
        target     | field      | val
        'nullable' | 'hash' | null
        'nullable' | 'hash' | getEmptyString()
        'nullable' | 'citation' | null
        'nullable' | 'citation' | getEmptyString()
        'maxSize'  | 'citation' | getCustomTextWithLength(501)
        'valid'    | 'citation' | getCustomTextWithLength(500)
        'nullable' | 'content'  | null
        'nullable' | 'content'  | getEmptyString()
        'maxSize'  | 'content'  | getCustomTextWithLength(5001)
        'valid'    | 'content'  | getCustomTextWithLength(5000)
        'nullable' | 'creator'  | null
        'nullable' | 'year'     | null
    }
}
