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
        entity nullable: true, validator: { Entity e, ReferenceVote rv ->
            if (rv.user == null) return
            boolean existing = false
            ReferenceVote.withNewSession {
                existing = ReferenceVote.exists(rv.reference.id, e.id, rv.user.id)
            }
            if (existing) {
                return 'referenceVote.exists'
            }
        }
    }

    static mapping = {
        version false
    }

    static boolean exists(long refId, long entityId, long userId) {
        where {
            reference == Reference.load(refId) && entity == Entity.load(entityId) && user == User.load(userId)
        }.count() > 0
    }
}
