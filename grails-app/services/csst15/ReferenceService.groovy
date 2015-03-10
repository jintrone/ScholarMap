package csst15

import csst15.command.ReferenceCommand
import csst15.security.User
import grails.transaction.Transactional

@Transactional
class ReferenceService {
    def springSecurityService

    def createReference(ReferenceCommand command) {
        def reference = new Reference()

        reference.properties = command.properties
        reference.creator = springSecurityService.currentUser as User

        if (reference.save(flush: true)) {
            log.info("Created reference with id ${reference.id}")
            return reference
        } else {
            log.error("Reference creation attempt failed")
            log.error(reference.errors.dump())
        }

        return null
    }
}
