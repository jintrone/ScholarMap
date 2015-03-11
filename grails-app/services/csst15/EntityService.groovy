package csst15

import csst15.command.EntityCommand
import csst15.security.User
import grails.transaction.Transactional

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
                user.addToEntities(entity)
                return entity
            } else {
                log.error("User creation attempt failed")
                log.error(entity.errors.dump())
            }
        }

        return null
    }
}
