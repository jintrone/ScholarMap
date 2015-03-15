package csst15

import csst15.command.EntityCommand
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.util.logging.Slf4j

@Slf4j
@Transactional(readOnly = true)
class EntityController {
    def entityService

    static allowedMethods = [
            submit: 'POST',
            view  : 'GET',
            create: 'GET',
            edit  : 'GET',
            update: 'POST'
    ]

    @Secured(['ROLE_USER'])
    def create() {
    }

    @Transactional
    @Secured(['ROLE_USER'])
    def submit(EntityCommand command) {
        if (!command.validate()) {
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

    def view() {
        if (params.id) {
            def entity = Entity.findById(params.id)
            [entity: entity]
        } else {
            redirect(controller: 'home', action: 'entities')
        }
    }

    @Secured(['ROLE_USER'])
    def edit() {
        if (params.id) {
            def entity = Entity.findById(params.id)
            [entity: entity]
        } else {
            redirect(controller: 'home', action: 'entities')
        }
    }

    @Secured(['ROLE_USER'])
    @Transactional
    def update(EntityCommand command) {
        if (command.hasErrors()) {
            render(view: 'edit', model: [command: command])
        } else {
            if (params.entityId) {
                def entity = Entity.findById(params.entityId)

                if (entityService.updateEntity(entity, command)) {
                    redirect(action: 'view', params: [id: entity.id])
                }
            } else {
                log.info("Reference id not found")
                redirect(controller: 'home', action: 'entities')
            }
        }
    }
}
