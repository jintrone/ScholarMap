package csst15

import csst15.command.EntityCommand
import csst15.security.User
import grails.transaction.Transactional
import groovy.util.logging.Slf4j

@Slf4j
@Transactional
class EntityService {
    def springSecurityService

    def createEntity(EntityCommand command) {
        def user = springSecurityService.currentUser as User
        def entity = new Entity()

        if (user) {
            entity.properties = command.properties

            if (entity.save(flush: true)) {
                log.info("Created entity with id ${entity.id}")
                UserEntity.create(user, entity)
                return entity
            } else {
                log.error("User creation attempt failed")
                log.error(entity.errors.dump())
            }
        }

        return null
    }

    def updateEntity(Entity entity, EntityCommand command) {
        entity.properties = command.properties

        if (entity.save(flush: true)) {
            log.info("Updated entity with id ${entity.id}")
            return true
        }

        log.error("Failed to update entity with id ${entity.id}")
        return false
    }
}
