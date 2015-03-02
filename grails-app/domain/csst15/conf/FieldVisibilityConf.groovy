package csst15.conf

import csst15.security.User

class FieldVisibilityConf {
    User user
    Boolean isUsernameVisible = true
    Boolean isFirstNameVisible = true
    Boolean isLastNameVisible = true
    Boolean isEmailVisible = true
    Boolean isDegreeYearVisible = true
    Boolean isInstitutionVisible = true
    Boolean isSpecializationVisible = true
    Boolean isPositionVisible = true
    Boolean isDepartmentVisible = true
    Boolean isPhotoVisible = true

    static constraints = {
    }

    static mapping = {
        version false
    }
}
