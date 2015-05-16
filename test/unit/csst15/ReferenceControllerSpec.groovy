package csst15

import csst15.command.ReferenceCommand
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(ReferenceController)
@Build(Reference)
class ReferenceControllerSpec extends Specification {
    void "test create action"() {
        expect:
        controller.create() == null
    }

    void "test submit action when reference creation failed"() {
        setup:
        request.method = 'POST'
        def refCmd = new ReferenceCommand(year: '2014', citation: 'citation')
        controller.referenceService = [createReference: { command -> null }]

        when:
        controller.submit(refCmd)

        then:
        controller.modelAndView.viewName == "/reference/create"
        controller.modelAndView.model.referenceCommand == refCmd
    }

    void "test submit action when reference created"() {
        setup:
        request.method = 'POST'
        def refCmd = new ReferenceCommand(year: '2014', citation: 'citation')
        def ref = Reference.build()
        controller.referenceService = [createReference: { command -> ref }]

        when:
        controller.submit(refCmd)

        then:
        response.redirectedUrl == '/home/entities'
    }
}
