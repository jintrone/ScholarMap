package csst15

import csst15.command.ReferenceCommand
import csst15.security.User
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.util.logging.Slf4j
import org.springframework.http.HttpStatus

@Slf4j
class InterestsController {
    def springSecurityService
    def referenceService

    static allowedMethods = [
            deleteInterest: 'POST',
            addInterest   : "POST"
    ]

    @Transactional
    @Secured(['IS_AUTHENTICATED_FULLY'])
    def addInterest() {
        def currentUser = springSecurityService.currentUser as User

        if (currentUser) {
            def entity = Entity.findWhere(name: params.name) ?: new Entity(type: GeneralUtils.constructEntityType(params.type), name: params.name, description: params.description).save(flush: true)
            if (entity?.id) {
                currentUser.addToEntities(entity)
                def entities = currentUser.entities
                render(template: '/user/interestRecords', model: [newEntity: entity, entities: entities, currentUser: currentUser])
            } else {
                render(status: HttpStatus.BAD_REQUEST)
            }

        } else {
            redirect(controller: 'login', action: 'auth')
        }
    }

    @Transactional
    @Secured(['IS_AUTHENTICATED_FULLY'])
    def deleteInterest() {
        def entityId = params.entityId
        def currentUser = springSecurityService.currentUser as User
        def entity = Entity.get(entityId)
        if (currentUser) {
            if (entityId) {
                ReferenceVote.findAllByEntityAndUser(entity, currentUser).collect { it.delete(flush: true) }
                currentUser.removeFromEntities(entity)
                log.info("Deleted the interest with id ${entityId} for user with id ${currentUser.id}")
                redirect(controller: 'user', action: 'interests', params: [username: currentUser.username])
            }
        } else {
            redirect(controller: 'login', action: 'auth')
        }
    }


    @Transactional
    @Secured(['IS_AUTHENTICATED_FULLY'])
    def referenceVote() {
        def reference = Reference.get(params.refId)
        def entity = Entity.get(params.entity)
        def currentUser = springSecurityService.currentUser as User

        if (reference && entity) {
            new ReferenceVote(user: currentUser, reference: reference, entity: entity).save(flush: true)
            def allReferences = Reference.list()
            def selectedReferences = ReferenceVote.findAllByEntityAndReferenceIsNotNull(entity)?.reference?.unique()
            def availableReferences = allReferences.findAll { ref ->
                !selectedReferences.contains(ref)
            }
            render(template: '/user/referenceList', model: [entityId: entity.id, selectedReferences: selectedReferences, availableReferences: availableReferences])
        } else {
            render(status: HttpStatus.BAD_REQUEST)
        }
    }

    @Transactional
    @Secured(['IS_AUTHENTICATED_FULLY'])
    def removeVote() {
        def reference = Reference.findById(params.id)
        def entity = Entity.findById(params.entityId)
        if (entity && reference) {
            ReferenceVote.findByEntityAndReference(entity, reference).collect { it.delete(flush: true) }
            log.info("Downvoted the entity with id ${entity.id}")
            redirect(action: 'references', params: [entityId: entity.id])
        } else {
            redirect(uri: '/not-found')
        }
    }


    def references() {
        def allReferences = Reference.list()
        def entity = Entity.findById(params.entityId)
        if (entity) {
            def selectedReferences = ReferenceVote.findAllByEntityAndReferenceIsNotNull(entity)?.reference?.unique()
            def availableReferences = allReferences.findAll { reference ->
                !selectedReferences.contains(reference)
            }
            render(view: '/user/references', model: [entity: entity, availableReferences: availableReferences, selectedReferences: selectedReferences])
        } else {
            redirect(uri: '/not-found')
        }
    }

    @Transactional
    @Secured(['IS_AUTHENTICATED_FULLY'])
    def addReference(ReferenceCommand referenceCommand) {
        if (referenceCommand.hasErrors()) {
            render(view: 'create', model: [referenceCommand: referenceCommand])
        } else {
            def reference = referenceService.createReference(referenceCommand)
            def entity = Entity.get(params.entity)
            def currentUser = springSecurityService.currentUser as User
            new ReferenceVote(user: currentUser, reference: reference, entity: entity).save(flush: true)
            if (reference) {
                redirect(action: 'references', params: [entityId: entity.id])
            } else {
                render(view: 'create', model: [referenceCommand: referenceCommand])
            }
        }
    }
}
