package csst15

import csst15.command.ReferenceCommand
import csst15.security.User
import grails.transaction.Transactional
import groovy.util.logging.Slf4j

@Slf4j
@Transactional
class ReferenceService {
    def springSecurityService

    def createReference(ReferenceCommand command) {
        def reference = new Reference()

        reference.properties = command.properties
        reference.creator = springSecurityService.currentUser as User
        reference.hash = GeneralUtils.generateMD5(reference.citation)

        if (reference.save(flush: true)) {
            log.info("Created reference with id ${reference.id}")
            return reference
        } else {
            log.error("Reference creation attempt failed")
            log.error(reference.errors.dump())
        }

        return null
    }

    def updateReference(Reference reference, ReferenceCommand command) {
        reference.properties = command.properties

        if (reference.save(flush: true)) {
            log.info("Updated reference with id ${reference.id}")
            return true
        }

        log.error("Failed to update reference with id ${reference.id}")
        return false
    }
}
