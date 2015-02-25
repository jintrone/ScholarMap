package csst15.conf

import csst15.security.User

class FieldMandatoryConf {
    User user
    Boolean isUsernameMandatory = false
    Boolean isFirstNameMandatory = false
    Boolean isLastNameMandatory = false
    Boolean isEmailMandatory = false
    Boolean isDegreeYearMandatory = false
    Boolean isInstitutionMandatory = false
    Boolean isSpecializationMandatory = false
    Boolean isPositionMandatory = false
    Boolean isDepartmentMandatory = false
    Boolean isPhotoMandatory = false

    static constraints = {
    }

    static mapping = {
        version false
    }
}
