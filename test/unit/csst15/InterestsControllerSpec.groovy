package csst15

import csst15.security.User
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import org.springframework.http.HttpStatus
import spock.lang.Specification

@TestFor(InterestsController)
@Build([User, Entity, UserEntity])
class InterestsControllerSpec extends Specification {
    void "test addInterest with invalid user"() {
        setup:
        request.method = "POST"
        controller.springSecurityService = [currentUser: null]

        when:
        controller.addInterest()

        then:
        response.redirectedUrl == "/login/auth"
    }

    void "test addInterest with valid user"() {
        setup:
        request.method = "POST"
        params.name = "entity"
        def user = User.build(username: 'emilan')
        controller.springSecurityService = [currentUser: user]
        def entity = Entity.build(name: 'entity', id: 1)
        Entity.metaClass.'static'.findWhere = { name -> entity }
        def eu = UserEntity.build(user: user, entity: entity)
        UserEntity.metaClass.'static'.findAllByUser = { cuser -> eu}

        when:
        controller.addInterest()

        then:
        response.status == HttpStatus.BAD_REQUEST.value()
    }
}
