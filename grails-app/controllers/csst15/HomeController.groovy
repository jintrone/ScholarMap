package csst15

import csst15.constants.EntityType
import csst15.constants.Roles
import csst15.security.Role
import csst15.security.UserRole
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.json.JsonOutput

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
@Transactional(readOnly = true)
class HomeController {
    static allowedMethods = [
            index: 'GET'
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
        if (request.method == "POST") {
            def c = Entity.createCriteria()
            def allAreas = c.list(max: Integer.parseInt(params.length), offset: params.start) {
                eq("type", EntityType.FIELD)
                or {
                    ilike("name", "%${params.'search[value]'}%")
                    ilike("description", "%${params.'search[value]'}%")
                }
                order("name", params."order[0][dir]")
            }
            def count = Entity.createCriteria().count() {
                eq("type", EntityType.FIELD)
                or {
                    ilike("name", "%${params.'search[value]'}%")
                    ilike("description", "%${params.'search[value]'}%")
                }
            }

            def json = JsonOutput.toJson(
                    draw: params.draw,
                    recordsTotal: allAreas.totalCount,
                    recordsFiltered: count,
                    data:
                            allAreas.collect { area ->
                                [
                                        interest   : "${ReferenceVote.findAllByEntity(area)?.user?.unique()?.size()}",
                                        name       : area.name,
                                        description: area.description,
                                        references : "${ReferenceVote.findAllByReferenceNotIsNullAndEntity(area)?.reference?.unique()?.size()}"
                                ]
                            }
            )

            render(json)
        } else {
            render(view: 'areas')
        }
    }

    def theories() {
        if (request.method == "POST") {
            def c = Entity.createCriteria()
            def allTheories = c.list(max: Integer.parseInt(params.length), offset: params.start) {
                eq("type", EntityType.THEORY)
                or {
                    ilike("name", "%${params.'search[value]'}%")
                    ilike("description", "%${params.'search[value]'}%")
                }
                order("name", params."order[0][dir]")
            }
            def count = Entity.createCriteria().count() {
                eq("type", EntityType.THEORY)
                or {
                    ilike("name", "%${params.'search[value]'}%")
                    ilike("description", "%${params.'search[value]'}%")
                }
            }

            def json = JsonOutput.toJson(
                    draw: params.draw,
                    recordsTotal: allTheories.totalCount,
                    recordsFiltered: count,
                    data:
                            allTheories.collect { theory ->
                                [
                                        interest   : "${ReferenceVote.findAllByEntity(theory)?.user?.unique()?.size()}",
                                        name       : theory.name,
                                        description: theory.description,
                                        references : "${ReferenceVote.findAllByReferenceNotIsNullAndEntity(theory)?.reference?.unique()?.size()}"
                                ]
                            }
            )

            render(json)
        } else {
            render(view: 'theories')
        }
    }

    def methods() {
        if (request.method == "POST") {
            def c = Entity.createCriteria()
            def allMethods = c.list(max: Integer.parseInt(params.length), offset: params.start) {
                eq("type", EntityType.METHOD)
                or {
                    ilike("name", "%${params.'search[value]'}%")
                    ilike("description", "%${params.'search[value]'}%")
                }
                order("name", params."order[0][dir]")
            }
            def count = Entity.createCriteria().count() {
                eq("type", EntityType.METHOD)
                or {
                    ilike("name", "%${params.'search[value]'}%")
                    ilike("description", "%${params.'search[value]'}%")
                }
            }

            def json = JsonOutput.toJson(
                    draw: params.draw,
                    recordsTotal: allMethods.totalCount,
                    recordsFiltered: count,
                    data:
                            allMethods.collect { method ->
                                [
                                        interest   : "${ReferenceVote.findAllByEntity(method)?.user?.unique()?.size()}",
                                        name       : method.name,
                                        description: method.description,
                                        references : "${ReferenceVote.findAllByReferenceNotIsNullAndEntity(method)?.reference?.unique()?.size()}"
                                ]
                            }
            )

            render(json)
        } else {
            render(view: 'methods')
        }
    }

    def venues() {
        if (request.method == "POST") {
            def c = Entity.createCriteria()
            def allVenues = c.list(max: Integer.parseInt(params.length), offset: params.start) {
                eq("type", EntityType.VENUE)
                or {
                    ilike("name", "%${params.'search[value]'}%")
                    ilike("description", "%${params.'search[value]'}%")
                }
                order("name", params."order[0][dir]")
            }
            def count = Entity.createCriteria().count() {
                eq("type", EntityType.VENUE)
                or {
                    ilike("name", "%${params.'search[value]'}%")
                    ilike("description", "%${params.'search[value]'}%")
                }
            }

            def json = JsonOutput.toJson(
                    draw: params.draw,
                    recordsTotal: allVenues.totalCount,
                    recordsFiltered: count,
                    data:
                            allVenues.collect { venue ->
                                [
                                        interest   : "${ReferenceVote.findAllByEntity(venue)?.user?.unique()?.size()}",
                                        name       : venue.name,
                                        description: venue.description,
                                        references : "${ReferenceVote.findAllByReferenceNotIsNullAndEntity(venue)?.reference?.unique()?.size()}"
                                ]
                            }
            )

            render(json)
        } else {
            render(view: 'venues')
        }
    }

    def references() {
        def references = Reference.list()

        [references: references]
    }
}
