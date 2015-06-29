package csst15

import csst15.command.ReferenceCommand
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.util.logging.Slf4j
import org.springframework.http.HttpStatus

@Slf4j
@Transactional(readOnly = true)
class ReferenceController {
    def referenceService

    static allowedMethods = [
            submit: 'POST',
            create: 'GET',
            view  : 'GET',
            edit  : 'GET',
            update: 'POST'
    ]

    @Secured(['ROLE_USER'])
    def create() {}

    @Transactional
    @Secured(['ROLE_USER'])
    def submit(ReferenceCommand referenceCommand) {
        if (referenceCommand.hasErrors()) {
            render(view: 'create', model: [referenceCommand: referenceCommand])
        } else {
            def reference = referenceService.createReference(referenceCommand)

            if (reference) {
                redirect(controller: 'home', action: 'references')
            } else {
                render(view: 'create', model: [referenceCommand: referenceCommand])
            }
        }
    }

    def view() {
        if (params.id) {
            def reference = Reference.findById(params.id)
            def refAuthor = ReferenceAuthor.findAll("from ReferenceAuthor as b where b.reference=? order by b.authorOrder", [reference])
//            def refAuthor = ReferenceAuthor.findAllByReference(reference)
            [reference: reference, refAuthor: refAuthor]
        } else {
            log.info("Reference id not found")
            redirect(uri: '/not-found')
        }
    }

    @Secured(['ROLE_USER'])
    def edit() {
        if (params.id) {
            def reference = Reference.findById(params.id)
            [reference: reference]
        } else {
            log.info("Reference id not found")
            redirect(uri: '/not-found')
        }
    }

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    @Transactional
    def update(ReferenceCommand command) {
//        if (command.hasErrors()) {
//            redirect(uri: '/not-found')
//        } else {
            if (params.referenceId) {
                def reference = Reference.findById(params.referenceId)
                def refAuthor = ReferenceAuthor.findAllByReference(reference)

                referenceService.updateReference(refAuthor, reference, params)
                redirect(action: 'view', params: [id: reference.id])
            } else {
                log.info("Reference id not found")
                redirect(uri: '/not-found')
            }
//        }
    }

    @Transactional
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def reorderAuthors() {
        def reference = Reference.get(params.refId)
        params.authorSort.eachWithIndex { id, index ->
            def author = Author.get(id)
            println "=================="
            println index
            println "=================="

            def refAuthor = ReferenceAuthor.findByAuthorAndReference(author, reference)
            refAuthor.authorOrder = index + 1
        }
        render HttpStatus.OK
    }
}
