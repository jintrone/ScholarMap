package csst15

import csst15.security.User
import groovy.transform.ToString

@ToString(includeNames = true)
class ReferenceVote implements Serializable {
    Entity entity
    Reference reference
    User user
    Date dateCreated

    static constraints = {
        reference nullable: true
        entity nullable: true
    }

    static mapping = {
        version false
    }
}
