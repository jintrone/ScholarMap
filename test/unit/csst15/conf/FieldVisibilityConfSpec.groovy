package csst15.conf

import csst15.utils.ConstraintsUnitSpec
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Unroll

@TestFor(FieldVisibilityConf)
@Build([FieldVisibilityConf, FieldMandatoryConf])
class FieldVisibilityConfSpec extends ConstraintsUnitSpec {
    def setup() {
        mockForConstraintsTests(FieldVisibilityConf, [FieldVisibilityConf.build()])
    }

    @Unroll("Tested FieldVisibilityConf field #field with the value #val")
    def "test FieldVisibilityConf fields' constraints"() {
        when:
        def obj = new FieldVisibilityConf("$field": val)

        then:
        validateConstraints(obj, field, target)

        where:
        target     | field                     | val
        'nullable' | 'isUsernameVisible'       | null
        'nullable' | 'isUsernameVisible'       | ''
        'nullable' | 'isFirstNameVisible'      | ''
        'nullable' | 'isFirstNameVisible'      | null
        'nullable' | 'isLastNameVisible'       | ''
        'nullable' | 'isLastNameVisible'       | null
        'nullable' | 'isEmailVisible'          | ''
        'nullable' | 'isEmailVisible'          | null
        'nullable' | 'isDegreeYearVisible'     | ''
        'nullable' | 'isDegreeYearVisible'     | null
        'nullable' | 'isInstitutionVisible'    | ''
        'nullable' | 'isInstitutionVisible'    | null
        'nullable' | 'isSpecializationVisible' | ''
        'nullable' | 'isSpecializationVisible' | null
        'nullable' | 'isPositionVisible'       | ''
        'nullable' | 'isPositionVisible'       | null
        'nullable' | 'isDepartmentVisible'     | ''
        'nullable' | 'isDepartmentVisible'     | null
        'nullable' | 'isPhotoVisible'          | ''
        'nullable' | 'isPhotoVisible'          | null
    }
}
