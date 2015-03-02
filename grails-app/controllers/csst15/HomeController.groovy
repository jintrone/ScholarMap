package csst15

import csst15.constants.Roles
import csst15.security.Role
import csst15.security.UserRole
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
@Transactional(readOnly = true)
class HomeController {
    static allowedMethods = [index: 'GET']

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
}
