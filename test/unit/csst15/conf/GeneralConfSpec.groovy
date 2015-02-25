package csst15.conf

import csst15.utils.ConstraintsUnitSpec
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Unroll

@TestFor(GeneralConf)
@Build(GeneralConf)
class GeneralConfSpec extends ConstraintsUnitSpec {
    def setup() {
        mockForConstraintsTests(GeneralConf, [GeneralConf.build(isRegEnabled: true)])
    }

    @Unroll("Tested GeneralConf field #field with the value #val")
    def "test GeneralConf fields' constraints"() {
        when:
        def obj = new GeneralConf("$field": val)

        then:
        validateConstraints(obj, field, target)

        where:
        target     | field          | val
        'nullable' | 'isRegEnabled' | null
        'nullable' | 'isRegEnabled' | ''
    }
}