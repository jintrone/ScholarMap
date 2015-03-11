package csst15

import csst15.command.EntityCommand
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.util.logging.Slf4j

@Slf4j
@Transactional(readOnly = true)
@Secured(['ROLE_USER'])
class EntityController {
    def entityService

    static allowedMethods = [
            submit: 'POST'
    ]

    def create() {
    }

    @Transactional
    def submit(EntityCommand command) {
        if (command.hasErrors()) {
            render(view: 'create', model: [command: command])
        } else {
            def entity = entityService.createEntity(command)

            if (entity) {
                redirect(controller: 'home', action: 'entities')
            } else {
                render(view: 'create', model: [command: command])
            }
        }
    }
}
