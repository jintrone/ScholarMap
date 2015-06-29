package csst15

import csst15.command.ReferenceCommand
import csst15.security.User
import grails.transaction.Transactional
import groovy.util.logging.Slf4j
import org.apache.commons.lang3.StringUtils

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

    def updateReference(def referenceAuthor, Reference reference, def params) {
        reference.year = Integer.parseInt(params.year)
        reference.citation = params.citation
        reference.hash = GeneralUtils.generateMD5(reference.citation)

        params.authorsId.each { id ->
            def author = Author.findById(id)
            def names = []
            names = StringUtils.split(params."author_${id}", ' ')
            author.firstName = names[0]
            author.lastName = names[1]
            log.info("Updated author with id ${author.id}")
        }


        if (reference.save(flush: true)) {
            log.info("Updated reference with id ${reference.id}")
            return true
        }

        log.error("Failed to update reference with id ${reference.id}")
        return false
    }
}
