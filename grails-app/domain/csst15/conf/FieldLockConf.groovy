package csst15.conf

import csst15.security.User

class FieldLockConf {
    User user
    Boolean isUsernameLocked = false
    Boolean isFirstNameLocked = false
    Boolean isLastNameLocked = false
    Boolean isEmailLocked = false
    Boolean isDegreeYearLocked = false
    Boolean isInstitutionLocked = false
    Boolean isSpecializationLocked = false
    Boolean isPositionLocked = false
    Boolean isDepartmentLocked = false
    Boolean isPhotoLocked = false

    static constraints = {
    }

    static mapping = {
        version false
    }
}
