package csst15

import csst15.command.ReferenceCommand
import csst15.security.User
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(ReferenceService)
@Build([User, Reference])
class ReferenceServiceSpec extends Specification {

    void "test create reference method"() {
        given:
        def user = User.build()
        def citation = 'citation'
        def command = new ReferenceCommand(year: '2014', citation: citation)
        service.springSecurityService = [currentUser: user]

        expect:
        service.createReference(command).creator == user
        service.createReference(command).citation == citation
    }
}
