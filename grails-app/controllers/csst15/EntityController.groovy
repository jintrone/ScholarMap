package csst15

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.util.logging.Slf4j

@Slf4j
@Transactional(readOnly = true)
@Secured(['ROLE_USER'])
class EntityController {
    def index() {
    }
}
