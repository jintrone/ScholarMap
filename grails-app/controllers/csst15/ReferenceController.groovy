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
            view  : 'GET',
            edit  : 'GET',
            update: 'POST'
    ]

    @Secured(['ROLE_USER'])
    def create() {}

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

    @Secured(['ROLE_USER'])
    def edit() {
        if (params.id) {
            def reference = Reference.findById(params.id)
            [reference: reference]
        } else {
            redirect(controller: 'home', action: 'entities')
        }
    }

    @Secured(['ROLE_USER'])
    @Transactional
    def update(ReferenceCommand command) {
        if (command.hasErrors()) {
            render(view: 'edit', [reference: command])
        } else {
            if (params.referenceId) {
                def reference = Reference.findById(params.referenceId)

                if (referenceService.updateReference(reference, command)) {
                    redirect(action: 'view', params: [id: reference.id])
                }
            } else {
                log.info("Reference id not found")
                redirect(controller: 'home', action: 'entities')
            }
        }
    }
}