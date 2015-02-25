package csst15.conf

import csst15.utils.ConstraintsUnitSpec
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Unroll

@TestFor(FieldMandatoryConf)
@Build(FieldMandatoryConf)
class FieldMandatoryConfSpec extends ConstraintsUnitSpec {
    def setup() {
        mockForConstraintsTests(FieldMandatoryConf, [FieldMandatoryConf.build()])
    }

    @Unroll("Tested FieldMandatoryConf field #field with the value #val")
    def "test FieldMandatoryConf fields' constraints"() {
        when:
        def obj = new FieldMandatoryConf("$field": val)

        then:
        validateConstraints(obj, field, target)

        where:
        target     | field                       | val
        'nullable' | 'isUsernameMandatory'       | null
        'nullable' | 'isUsernameMandatory'       | ''
        'nullable' | 'isFirstNameMandatory'      | ''
        'nullable' | 'isFirstNameMandatory'      | null
        'nullable' | 'isLastNameMandatory'       | ''
        'nullable' | 'isLastNameMandatory'       | null
        'nullable' | 'isEmailMandatory'          | ''
        'nullable' | 'isEmailMandatory'          | null
        'nullable' | 'isDegreeYearMandatory'     | ''
        'nullable' | 'isDegreeYearMandatory'     | null
        'nullable' | 'isInstitutionMandatory'    | ''
        'nullable' | 'isInstitutionMandatory'    | null
        'nullable' | 'isSpecializationMandatory' | ''
        'nullable' | 'isSpecializationMandatory' | null
        'nullable' | 'isPositionMandatory'       | ''
        'nullable' | 'isPositionMandatory'       | null
        'nullable' | 'isDepartmentMandatory'     | ''
        'nullable' | 'isDepartmentMandatory'     | null
        'nullable' | 'isPhotoMandatory'          | ''
        'nullable' | 'isPhotoMandatory'          | null
    }
}
