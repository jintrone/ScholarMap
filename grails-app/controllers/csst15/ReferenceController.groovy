package csst15

import csst15.command.ReferenceCommand
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.util.logging.Slf4j

@Slf4j
@Transactional(readOnly = true)
class ReferenceController {
    def referenceService

    static allowedMethods = [
            submit: 'POST',
            create: 'GET',
            view  : 'GET'
    ]

    @Secured(['ROLE_USER'])
    def create() {
        []
    }

    @Transactional
    @Secured(['ROLE_USER'])
    def submit(ReferenceCommand referenceCommand) {
        if (referenceCommand.hasErrors()) {
            render(view: 'create', model: [referenceCommand: referenceCommand])
        } else {
            def reference = referenceService.createReference(referenceCommand)

            if (reference) {
                redirect(controller: 'home', action: 'entities')
            } else {
                render(view: 'create', model: [referenceCommand: referenceCommand])
            }
        }
    }

    def view() {
        if (params.id) {
            def reference = Reference.findById(params.id)

            [reference: reference]
        } else {
            redirect(controller: 'home', action: 'entities')
        }
    }
}
