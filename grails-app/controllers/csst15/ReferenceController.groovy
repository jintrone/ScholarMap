package csst15

import csst15.command.ReferenceCommand
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.util.logging.Slf4j

@Slf4j
@Transactional(readOnly = true)
@Secured(['ROLE_USER'])
class ReferenceController {
    def referenceService

    static allowedMethods = [
            submitReference: 'POST'
    ]

    def create() {
        []
    }

    @Transactional
    def submit(ReferenceCommand referenceCommand) {
        if (referenceCommand.hasErrors()) {
            render(view: 'create', model: [referenceCommand: referenceCommand])
        } else {
            def reference = referenceService.createReference(referenceCommand)

            if (reference) {
                redirect(controller: 'home')
            } else {
                render(view: 'create', model: [referenceCommand: referenceCommand])
            }
        }
    }
}
