package csst15

import csst15.constants.Roles
import csst15.security.Role
import csst15.security.User
import csst15.security.UserRole
import grails.converters.JSON
import grails.rest.RestfulController
import groovy.json.JsonBuilder

import static csst15.GeneralUtils.constructOnlyParam
import static csst15.GeneralUtils.constructReferenceUrl
import static csst15.constants.EntityType.*

class GraphController extends RestfulController {
    static responseFormats = ['json', 'xml']

    GraphController() {
        super(User)
    }

    def charGraph() {
        def entityList = null
        if (params.only)
            entityList = params.only.tokenize(',')



        def entities = Entity.list()
        def references = Reference.list()
        def users = UserRole.findAllByRole(Role.findByAuthority(Roles.USER.name))*.user.unique()

        def builder = new JsonBuilder()
        def root = builder {
            nodes(
                    entities.collect { Entity entity ->
                        if (!entityList || (entityList && constructOnlyParam(entity.type) in entityList)) {
                            [
                                    name        : entity.name,
                                    type        : entity.type,
                                    relative_url: constructReferenceUrl(entity.type, entity.name),
                                    people      : (ReferenceVote.findAllByEntity(entity).user.unique().id),
                                    references  :
                                            ReferenceVote.findAllByEntity(entity).reference.unique().collect {
                                                [id: it.id]
                                            }
                            ]
                        }
                    }
            )


            attributes(
                    people:
                            users.collect { u ->
                                [
                                        id          : u.id,
                                        name        : u.firstName + " " + u.lastName,
                                        relative_url: constructReferenceUrl("user", u.username)
                                ]
                            },
                    references:
                            references.collect { Reference reference ->
                                [id: reference.id, name: reference.citation, relative_url: constructReferenceUrl("reference", reference.citation.substring(0, 10))]
                            }
            )
        }

        render(root as JSON)
    }

    def refGraph() {
        def referencesAuthor = ReferenceAuthor.findAll()
        def referencesVote = ReferenceVote.findAll()
        def allMethods = Entity.findAllByType(METHOD)
        def allTheories = Entity.findAllByType(THEORY)
        def allVenues = Entity.findAllByType(VENUE)
        def allFields = Entity.findAllByType(FIELD)

        def builder = new JsonBuilder()
        def root = builder {
            nodes(
                    referencesAuthor.reference.unique().collect { Reference reference ->
                        def authors = referencesAuthor.findAll { ReferenceAuthor refAuth -> refAuth.reference == reference }.author
                        def entities = referencesVote.findAll { ReferenceVote refVote -> refVote.reference == reference }.entity
                        def methods = entities.findAll { Entity method -> method.type == METHOD }
                        def theories = entities.findAll { Entity theory -> theory.type == THEORY }
                        def fields = entities.findAll { Entity field -> field.type == FIELD }
                        def venues = entities.findAll { Entity venue -> venue.type == VENUE }
                        [
                                citation    : reference.citation,
                                year        : reference.year,
                                authors     : authors.firstName,
                                department  : reference.creator?.department?.title ?: "",
                                relative_url: constructReferenceUrl("reference", reference.citation.substring(0, 10)),
                                methods     : (methods.id),
                                fields      : (fields.id),
                                venues      : (venues.id),
                                theories    : (theories.id)
                        ]
                    }
            )

            attributes(
                    methods:
                            allMethods.collect { Entity method ->
                                [id: method.id, name: method.name, relative_url: constructReferenceUrl(METHOD, method.name)]
                            },

                    theories:
                            allTheories.collect { Entity theory ->
                                [id: theory.id, name: theory.name, relative_url: constructReferenceUrl(THEORY, theory.name)]
                            },

                    fields:
                            allFields.collect { Entity field ->
                                [id: field.id, name: field.name, relative_url: constructReferenceUrl(FIELD, field.name)]
                            },

                    venues:
                            allVenues.collect { Entity venue ->
                                [id: venue.id, name: venue.name, relative_url: constructReferenceUrl(VENUE, venue.name)]
                            }
            )
        }

        render(root as JSON)
    }

    def peopleGraph() {
        def users = UserRole.findAllByRole(Role.findByAuthority(Roles.USER.name))*.user.unique()
        def methods = Entity.findAllByType(METHOD)
        def theories = Entity.findAllByType(THEORY)
        def venues = Entity.findAllByType(VENUE)
        def fields = Entity.findAllByType(FIELD)
        def references = Reference.list()

        def builder = new JsonBuilder()
        def root = builder {
            nodes(
                    users.collect { User u ->
                        def userMethods = u.entities.findAll { Entity method -> method.type == METHOD }
                        def userTheories = u.entities.findAll { Entity theory -> theory.type == THEORY }
                        def userFields = u.entities.findAll { Entity field -> field.type == FIELD }
                        def userVenues = u.entities.findAll { Entity venue -> venue.type == VENUE }
                        [
                                name        : u.firstName + " " + u.lastName,
                                department  : u.department?.title ?: "",
                                relative_url: constructReferenceUrl("user", u.username),
                                methods     : (userMethods.id),
                                fields      : (userFields.id),
                                venues      : (userVenues.id),
                                theories    : (userTheories.id)
                        ]
                    }
            )

            attributes(
                    methods:
                            methods.collect { Entity method ->
                                [id: method.id, name: method.name, relative_url: constructReferenceUrl(METHOD, method.name)]
                            },

                    theories:
                            theories.collect { Entity theory ->
                                [id: theory.id, name: theory.name, relative_url: constructReferenceUrl(THEORY, theory.name)]
                            },

                    fields:
                            fields.collect { Entity field ->
                                [id: field.id, name: field.name, relative_url: constructReferenceUrl(FIELD, field.name)]
                            },

                    venues:
                            venues.collect { Entity venue ->
                                [id: venue.id, name: venue.name, relative_url: constructReferenceUrl(VENUE, venue.name)]
                            },
                    references:
                            references.collect { Reference reference ->
                                [id: reference.id, name: reference.citation, relative_url: constructReferenceUrl("reference", reference.citation.substring(0, 10))]
                            }
            )
        }


        render(root as JSON)
    }
}
