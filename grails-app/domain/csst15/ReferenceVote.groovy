package csst15

import csst15.security.User

class ReferenceVote implements Serializable {
    Entity entity
    Reference reference
    User user

    static constraints = {
        reference nullable: true
        entity nullable: true
    }

    static mapping = {
        version false
    }
}
