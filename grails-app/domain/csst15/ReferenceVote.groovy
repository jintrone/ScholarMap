package csst15

import csst15.security.User

class ReferenceVote implements Serializable {
    Entity entity
    Reference reference
    User user

    static constraints = {
    }

    static mapping = {
        version false
    }
}
