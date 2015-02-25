package csst15.conf

import csst15.utils.ConstraintsUnitSpec
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Unroll

@TestFor(FieldLockConf)
@Build(FieldLockConf)
class FieldLockConfSpec extends ConstraintsUnitSpec {

    def setup() {
        mockForConstraintsTests(FieldLockConf, [FieldLockConf.build()])
    }

    @Unroll("Tested FieldLockConf field #field with the value #val")
    def "test FieldLockConf fields' constraints"() {
        when:
        def obj = new FieldLockConf("$field": val)

        then:
        validateConstraints(obj, field, target)

        where:
        target     | field                    | val
        'nullable' | 'isUsernameLocked'       | null
        'nullable' | 'isUsernameLocked'       | ''
        'nullable' | 'isFirstNameLocked'      | ''
        'nullable' | 'isFirstNameLocked'      | null
        'nullable' | 'isLastNameLocked'       | ''
        'nullable' | 'isLastNameLocked'       | null
        'nullable' | 'isEmailLocked'          | ''
        'nullable' | 'isEmailLocked'          | null
        'nullable' | 'isDegreeYearLocked'     | ''
        'nullable' | 'isDegreeYearLocked'     | null
        'nullable' | 'isInstitutionLocked'    | ''
        'nullable' | 'isInstitutionLocked'    | null
        'nullable' | 'isSpecializationLocked' | ''
        'nullable' | 'isSpecializationLocked' | null
        'nullable' | 'isPositionLocked'       | ''
        'nullable' | 'isPositionLocked'       | null
        'nullable' | 'isDepartmentLocked'     | ''
        'nullable' | 'isDepartmentLocked'     | null
        'nullable' | 'isPhotoLocked'          | ''
        'nullable' | 'isPhotoLocked'          | null
    }
}
