package csst15

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
}
