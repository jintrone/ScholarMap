package csst15

import csst15.command.EntityCommand
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(EntityController)
@Build(Entity)
class EntityControllerSpec extends Specification {
    void "test create action"() {
        expect:
        controller.create() == null
    }

    void "test the submit action with errors"() {
        setup:
        request.method = 'POST'
        def entityCommand = new EntityCommand()

        when:
        controller.submit(entityCommand)

        then:
        controller.modelAndView.model.command == entityCommand
        controller.modelAndView.viewName == '/entity/create'
    }

    void "test the submit action when failed to create"() {
        setup:
        request.method = 'POST'
        def entityCommand = new EntityCommand(name: 'Method1', description: 'Method1 desc')
        controller.entityService = [createEntity: { command -> null }]

        when:
        controller.submit(entityCommand)

        then:
        controller.modelAndView.model.command == entityCommand
        controller.modelAndView.viewName == '/entity/create'
    }

    void "test the submit action when successfully created"() {
        setup:
        request.method = 'POST'
        def entityCommand = new EntityCommand(name: 'Method2', description: 'Method2 desc')
        def entity = Entity.build()
        controller.entityService = [createEntity: { command -> entity }]

        when:
        controller.submit(entityCommand)

        then:
        response.redirectedUrl == '/home/entities'
    }
}
