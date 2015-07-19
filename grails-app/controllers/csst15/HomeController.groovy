package csst15

import csst15.constants.EntityType
import csst15.security.User
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
        def columns = ['0': 'username', '1': 'lastName', '2': 'title', '3': 'name']
        if (request.method == "POST") {
            def c = User.createCriteria()
            def allUsers = c.list(max: Integer.parseInt(params.length), offset: params.start) {
                createAlias('department', 'd')
                createAlias('position', 'p')
                gt("id", 1L)
                or {
                    ilike("username", "${params.'search[value]'}%")
                    ilike("firstName", "${params.'search[value]'}%")
                    ilike("lastName", "${params.'search[value]'}%")
                    ilike("d.title", "${params.'search[value]'}%")
                    ilike("p.name", "${params.'search[value]'}%")
                }

                if (params.'order[0][column]' == '2') {
                    order("d." + columns[params.'order[0][column]'], params."order[0][dir]")
                } else if (params.'order[0][column]' == '3') {
                    order("p." + columns[params.'order[0][column]'], params."order[0][dir]")
                } else {
                    order(columns[params.'order[0][column]'], params."order[0][dir]")
                }
            }
            def count = User.createCriteria().count() {
                createAlias('department', 'd')
                createAlias('position', 'p')
                gt("id", 1L)
                or {
                    ilike("username", "${params.'search[value]'}%")
                    ilike("firstName", "${params.'search[value]'}%")
                    ilike("lastName", "${params.'search[value]'}%")
                    ilike("d.title", "${params.'search[value]'}%")
                    ilike("p.name", "${params.'search[value]'}%")
                }
            }

            def json = JsonOutput.toJson(
                    draw: params.draw,
                    recordsTotal: allUsers.totalCount,
                    recordsFiltered: count,
                    data:
                            allUsers.collect { user ->
                                [
                                        username  : user.username,
                                        fullName  : "${user.firstName} ${user.lastName}",
                                        department: user.department?.title,
                                        position  : user.position?.name
                                ]
                            }
            )

            render(json)
        } else {
            render(view: '/list')
        }
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
        def columns = ['1': 'year', '2': 'citation']
        if (request.method == "POST") {
            def c = Reference.createCriteria()
            def allReferences = c.list(max: Integer.parseInt(params.length), offset: params.start) {
                ilike("citation", "${params.'search[value]'}%")
                if (params.'order[0][column]' == '0') {
                    params.'order[0][column]' = '1'
                    order(columns[params.'order[0][column]'], params."order[0][dir]")
                } else {
                    order(columns[params.'order[0][column]'], params."order[0][dir]")
                }
            }
            def count = Reference.createCriteria().count() {
                ilike("citation", "${params.'search[value]'}%")
            }

            def json = JsonOutput.toJson(
                    draw: params.draw,
                    recordsTotal: allReferences.totalCount,
                    recordsFiltered: count,
                    data:
                            allReferences.collect { reference ->
                                [
                                        year    : reference.year,
                                        citation: reference.citation,
                                        author  :
                                                ReferenceAuthor.findAllByReference(reference).author.collect { a ->
                                                    a.lastName + " " + a.firstName.getAt(0) + "."
                                                },
                                        votes   : "${ReferenceVote.findAllByReference(reference)?.unique()?.size()}",
                                        id      : reference.id
                                ]
                            }
            )

            render(json)
        } else {
            render(view: 'references')
        }

//        def references = Reference.list()
//
//        [references: references]
    }
}
