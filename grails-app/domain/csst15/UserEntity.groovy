package csst15

import csst15.security.User
import groovy.transform.ToString

@ToString(includeNames = true)
class UserEntity implements Serializable {
    User user
    Entity entity

    static constraints = {
        entity validator: { Entity e, UserEntity eu ->
            if (eu.user == null) return
            boolean existing = false
            UserEntity.withNewSession {
                existing = UserEntity.exists(eu.user.id, e.id)
            }
            if (existing) {
                return 'userEntity.exists'
            }
        }
    }

    static mapping = {
        id composite: ['entity', 'user']
        version false
    }

    static boolean exists(long userId, long entityId) {
        UserEntity.where {
            user == User.load(userId) &&
                    entity == Entity.load(entityId)
        }.count() > 0
    }

    static UserEntity create(User user, Entity entity, boolean flush = false) {
        def instance = new UserEntity(user: user, entity: entity)
        instance.save(flush: flush, insert: true)
        instance
    }

    static boolean remove(User u, Entity e, boolean flush = false) {
        if (u == null || e == null) return false

        int rowCount = UserEntity.where {
            user == User.load(u.id) &&
                    entity == Entity.load(e.id)
        }.deleteAll()

        if (flush) {
            UserEntity.withSession { it.flush() }
        }

        rowCount > 0
    }
}
