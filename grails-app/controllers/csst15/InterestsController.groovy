package csst15

import csst15.command.ReferenceCommand
import csst15.security.User
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import groovy.util.logging.Slf4j
import org.apache.commons.lang3.StringUtils
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Transactional

@Slf4j
class InterestsController {
    def springSecurityService
    def referenceService

    static allowedMethods = [
            deleteInterest        : 'POST',
            addInterest            : "POST",
            loadAvailableReferences: 'POST',
            loadSelectedReferences: 'POST',
            loadInterestsRecord   : 'POST'
    ]

    @Transactional
    @Secured(['IS_AUTHENTICATED_FULLY'])
    def addInterest() {
        def currentUser = springSecurityService.currentUser as User

        if (currentUser) {
            def existedEntity = Entity.findWhere(name: params.name)
            if (existedEntity) {
                existedEntity.description = params.description
                existedEntity.save(flush: true)
            }
            def entity = existedEntity ?: new Entity(type: GeneralUtils.constructEntityType(params.type), name: params.name, description: params.description).save(flush: true)
            if (entity?.id) {
                UserEntity.create(currentUser, entity, true)
                def entities = UserEntity.findAllByUser(currentUser)?.entity
                render(template: '/user/interestRecords', model: [newEntity: entity, entities: entities, user: currentUser])
            } else {
                render(status: HttpStatus.BAD_REQUEST)
            }

        } else {
            redirect(controller: 'login', action: 'auth')
        }
    }

    def loadInterestsRecord() {
        def user = User.findByUsername(params.username)
        if (user) {
            def entities = UserEntity.findAllByUser(user)?.entity
            render(template: '/user/interestRecords', model: [entities: entities, user: user])
        } else {
            redirect(uri: '/not-found')
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
                UserEntity.remove(currentUser, entity)
                log.info("Deleted the interest with id ${entityId} for user with id ${currentUser.id}")
                redirect(controller: 'user', action: 'profile', params: [username: currentUser.username])
            }
        } else {
            redirect(controller: 'login', action: 'auth')
        }
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def loadAvailableReferences() {
        def columns = ['0': 'lastName', '1': 'year', '2': 'citation']
        def entity = Entity.get(params.entity)
        def selectedReferences = ReferenceVote.findAllByEntityAndUser(entity, springSecurityService.currentUser as User, [cache: true])?.reference?.unique()
        def available = ReferenceAuthor.createCriteria().list(max: Integer.parseInt(params.length), offset: params.start) {
            createAlias('reference', 'r')
            createAlias('author', 'a')
            or {
                ilike("r.citation", "${params.'search[value]'}%")
                ilike("a.lastName", "${params.'search[value]'}%")
                if (StringUtils.isNumeric(params.'search[value]')) {
                    eq('r.year', Integer.parseInt(params.'search[value]'))
                }
            }

            if (params.'order[0][column]' == '0') {
                order("a." + columns[params.'order[0][column]'], params."order[0][dir]")
            } else {
                order("r." + columns[params.'order[0][column]'], params."order[0][dir]")
            }

            projections {
                distinct('reference')
            }
        }

        def availableReferences = available.findAll { reference ->
            !selectedReferences.id.contains(reference.id)
        }

        def count = ReferenceAuthor.createCriteria().list {
            createAlias('reference', 'r')
            createAlias('author', 'a')
            or {
                ilike("r.citation", "${params.'search[value]'}%")
                ilike("a.lastName", "${params.'search[value]'}%")
                if (StringUtils.isNumeric(params.'search[value]')) {
                    eq('r.year', Integer.parseInt(params.'search[value]'))
                }
            }

            projections {
                countDistinct('reference')
            }
        }

        def results = [
                draw           : params.draw,
                recordsTotal   : (available[0] ? available[0]?.count() - selectedReferences?.size() : 0),
                recordsFiltered: (count[0]),
                data           :
                        availableReferences.collect { reference ->
                            [
                                    year    : reference.year,
                                    citation: reference.citation,
                                    author  :
                                            ReferenceAuthor.findAllByReference(reference).author.collect { a ->
                                                a.lastName + " " + a.firstName.getAt(0) + "."
                                            },
                                    votes: "${ReferenceVote.findAllByReferenceAndEntity(reference, entity)?.unique()?.size()}",
                                    id   : "?entity=" + entity.id + "&id=" + reference.id
                            ]
                        }
        ]

        render(results as JSON)
    }

    def loadSelectedReferences() {
        def entity = Entity.get(params.entity)
        def user
        if (entity) {
            if (params.isOwner == "false") {
                user = User.get(params.user)
            } else {
                user = springSecurityService.currentUser as User
            }
            def selectedReferencesVote = ReferenceVote.createCriteria().list(max: Integer.parseInt(params.length), offset: params.start) {
                createAlias('reference', 'r')
                eq("entity", entity)
                eq("user", user)
                ilike("r.citation", "${params.'search[value]'}%")
            }

            def count = ReferenceVote.createCriteria().count() {
                createAlias('reference', 'r')
                eq("entity", entity)
                eq("user", user)
                ilike("r.citation", "${params.'search[value]'}%")
            }

            def results
            if (params.isOwner == "true") {
                results = [
                        draw           : params.draw,
                        recordsTotal   : selectedReferencesVote.totalCount,
                        recordsFiltered: count,
                        data           :
                                selectedReferencesVote.collect { vote ->
                                    [
                                            year    : vote.reference.year,
                                            citation: vote.reference.citation,
                                            author  :
                                                    ReferenceAuthor.findAllByReference(vote.reference).author.collect { a ->
                                                        a.lastName + " " + a.firstName.getAt(0) + "."
                                                    },
                                            votes: "${ReferenceVote.findAllByReferenceAndEntity(vote.reference, entity)?.unique()?.size()}",
                                            id   : "?entity=" + entity.id + "&id=" + vote.reference.id,
                                            isOwner : true
                                    ]
                                }
                ]
            } else {
                results = [
                        draw           : params.draw,
                        recordsTotal   : selectedReferencesVote.totalCount,
                        recordsFiltered: count,
                        data           :
                                selectedReferencesVote.collect { vote ->
                                    [
                                            year    : vote.reference.year,
                                            citation: vote.reference.citation,
                                            author  :
                                                    ReferenceAuthor.findAllByReference(vote.reference).author.collect { a ->
                                                        a.lastName + " " + a.firstName.getAt(0) + "."
                                                    },
                                            votes: "${ReferenceVote.findAllByReferenceAndEntity(vote.reference, entity)?.unique()?.size()}",
                                            id   : "?entity=" + entity.id + "&id=" + vote.reference.id
                                    ]
                                }
                ]
            }


            render(results as JSON)
        } else {
            redirect(uri: '/not-found')
        }

    }


    @Transactional
    @Secured(['IS_AUTHENTICATED_FULLY'])
    def referenceVote() {
        def reference = Reference.get(params.id)
        def entity = Entity.get(params.entity)
        def currentUser = springSecurityService.currentUser as User

        if (reference && entity) {
            ReferenceVote.findByReferenceAndEntityAndUser(reference, entity, currentUser) ?: new ReferenceVote(user: currentUser, reference: reference, entity: entity).save(flush: true)
            log.info("Voted the reference with id ${reference.id}")
            redirect(action: 'references', params: [user: currentUser.id, entityId: entity.id])
        } else {
            redirect(uri: '/not-found')
        }
    }

    @Transactional
    @Secured(['IS_AUTHENTICATED_FULLY'])
    def removeVote() {
        def reference = Reference.findById(params.id)
        def entity = Entity.findById(params.entity)
        if (entity && reference) {
            def currentUser = springSecurityService.currentUser as User
            ReferenceVote.findByEntityAndReference(entity, reference, [cache: true]).collect { it.delete(flush: true) }
            log.info("Downvoted the reference with id ${reference.id}")
            redirect(action: 'references', params: [user: currentUser.id, entityId: entity.id])
        } else {
            redirect(uri: '/not-found')
        }
    }


    def references() {
        def entity = Entity.findById(params.entityId)
        def user = User.get(params.user)
        if (entity && user) {
            render(view: '/user/references', model: [user: user, entity: entity])
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
            def reference = params.refId ? Reference.findById(params.refId) : referenceService.createReference(referenceCommand)
            def entity = Entity.get(params.entity)
            def authorName = []
            def author
            params.list('fullName').each { name ->
                authorName = StringUtils.split(name, ',')
                author = Author.findByFirstNameAndLastName(authorName[1].trim(), authorName[0].trim()) ?: new Author(firstName: authorName[1].trim(), lastName: authorName[0].trim()).save()
                new ReferenceAuthor(reference: reference, author: author).save(flush: true)
            }
            if (reference) {
                def currentUser = springSecurityService.currentUser as User
                if (!ReferenceVote.findByReferenceAndEntity(reference, entity)) {
                    new ReferenceVote(user: currentUser, reference: reference, entity: entity).save(flush: true)
                }
                redirect(action: 'references', params: [user: currentUser.id, entityId: entity.id])
            } else {
                render(view: 'create', model: [referenceCommand: referenceCommand])
            }
        }
    }
}
