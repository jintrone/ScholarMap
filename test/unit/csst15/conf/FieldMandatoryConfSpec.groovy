package csst15.conf

import csst15.utils.ConstraintsUnitSpec
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Unroll

@TestFor(FieldMandatoryConf)
@Build(FieldMandatoryConf)
class FieldMandatoryConfSpec extends ConstraintsUnitSpec {
    def setup() {
        mockForConstraintsTests(FieldMandatoryConf, [FieldMandatoryConf.build(fieldName: 'firstName')])
    }

    @Unroll("Tested FieldMandatoryConf field #field with the value #val")
    def "test FieldMandatoryConf fields' constraints"() {
        when:
        def obj = new FieldMandatoryConf("$field": val)

        then:
        validateConstraints(obj, field, target)

        where:
        target     | field         | val
        'nullable' | 'fieldName'   | null
        'nullable' | 'fieldName'   | ''
        'unique'   | 'fieldName'   | 'firstName'
        'nullable' | 'isMandatory' | ''
        'nullable' | 'isMandatory' | null
    }
}
