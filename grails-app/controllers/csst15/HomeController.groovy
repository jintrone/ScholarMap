package csst15

import csst15.constants.EntityType
import csst15.constants.Roles
import csst15.security.Role
import csst15.security.UserRole
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
@Transactional(readOnly = true)
class HomeController {
    static allowedMethods = [
            index: 'GET',
            areas: 'GET'
    ]

    def index() {
        render(view: '/index')
    }

    def about() {
        render(view: '/about')
    }

    def list() {
        def userList = UserRole.findAllByRole(Role.findByAuthority(Roles.USER.name)).user
        render(view: '/list', model: [users: userList])
    }

    def entities() {
        def entities = Entity.list()
        def references = Reference.list()

        render(view: '/entities', model: [entities: entities, references: references])
    }

    def areas() {
        def allAreas = Entity.findAllByType(EntityType.FIELD)

        [areas: allAreas]
    }

    def theories() {
        def allTheories = Entity.findAllByType(EntityType.THEORY)

        [theories: allTheories]
    }

    def methods() {
        def allMethods = Entity.findAllByType(EntityType.METHOD)

        [methods: allMethods]
    }

    def venues() {
        def allVenues = Entity.findAllByType(EntityType.VENUE)

        [venues: allVenues]
    }

    def references() {
        def references = Reference.list()

        [references: references]
    }
}
