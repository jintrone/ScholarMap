package csst15.conf

import csst15.security.User

class FieldVisibilityConf {
    User user
    Boolean isUsernameVisible = false
    Boolean isFirstNameVisible = false
    Boolean isLastNameVisible = false
    Boolean isEmailVisible = false
    Boolean isDegreeYearVisible = false
    Boolean isInstitutionVisible = false
    Boolean isSpecializationVisible = false
    Boolean isPositionVisible = false
    Boolean isDepartmentVisible = false
    Boolean isPhotoVisible = false

    static constraints = {
    }

    static mapping = {
        version false
    }
}
