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
        def entityCommand = new EntityCommand(type: 'Method')

        when:
        controller.submit(entityCommand)

        then:
        controller.modelAndView.model.command == entityCommand
        controller.modelAndView.viewName == '/entity/create'
    }
}
