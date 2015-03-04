package csst15

import csst15.constants.Roles
import csst15.security.Role
import csst15.security.User
import csst15.security.UserRole
import grails.converters.JSON
import grails.rest.RestfulController
import groovy.json.JsonBuilder

import static csst15.GeneralUtils.constructReferenceUrl

class GraphController extends RestfulController {
    static responseFormats = ['json', 'xml']

    GraphController() {
        super(User)
    }

    def charGraph() {
        def builder = new JsonBuilder()
        def root = builder {
            nodes(
            )
        }

        render(root as JSON)
    }

    def refGraph() {
        def referencesAuthor = ReferenceAuthor.findAll()
        def referencesVote = ReferenceVote.findAll()
        def allMethods = Entity.findAllByType("method")
        def allTheories = Entity.findAllByType("theory")
        def allVenues = Entity.findAllByType("venue")
        def allFields = Entity.findAllByType("field")

        def builder = new JsonBuilder()
        def root = builder {
            nodes(
                    referencesAuthor.reference.unique().collect { Reference reference ->
                        def authors = referencesAuthor.findAll { ReferenceAuthor refAuth -> refAuth.reference == reference }.author
                        def entities = referencesVote.findAll { ReferenceVote refVote -> refVote.reference == reference }.entity
                        def methods = entities.findAll { Entity method -> method.type == "method" }
                        def theories = entities.findAll { Entity theory -> theory.type == "theory" }
                        def fields = entities.findAll { Entity field -> field.type == "field" }
                        def venues = entities.findAll { Entity venue -> venue.type == "venue" }
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
                                [id: method.id, name: method.name, relative_url: constructReferenceUrl("method", method.name)]
                            },

                    theories:
                            allTheories.collect { Entity theory ->
                                [id: theory.id, name: theory.name, relative_url: constructReferenceUrl("theory", theory.name)]
                            },

                    fields:
                            allFields.collect { Entity field ->
                                [id: field.id, name: field.name, relative_url: constructReferenceUrl("field", field.name)]
                            },

                    venues:
                            allVenues.collect { Entity venue ->
                                [id: venue.id, name: venue.name, relative_url: constructReferenceUrl("venue", venue.name)]
                            }
            )
        }

        render(root as JSON)
    }

    def peopleGraph() {
        def users = UserRole.findAllByRole(Role.findByAuthority(Roles.USER.name))*.user.unique()
        def methods = Entity.findAllByType("method")
        def theories = Entity.findAllByType("theory")
        def venues = Entity.findAllByType("venue")
        def fields = Entity.findAllByType("field")
        def references = Reference.list()

        def builder = new JsonBuilder()
        def root = builder {
            nodes(
                    users.collect { User u ->
                        def userMethods = u.entities.findAll { Entity method -> method.type == "method" }
                        def userTheories = u.entities.findAll { Entity theory -> theory.type == "theory" }
                        def userFields = u.entities.findAll { Entity field -> field.type == "field" }
                        def userVenues = u.entities.findAll { Entity venue -> venue.type == "venue" }
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
                                [id: method.id, name: method.name, relative_url: constructReferenceUrl("method", method.name)]
                            },

                    theories:
                            theories.collect { Entity theory ->
                                [id: theory.id, name: theory.name, relative_url: constructReferenceUrl("theory", theory.name)]
                            },

                    fields:
                            fields.collect { Entity field ->
                                [id: field.id, name: field.name, relative_url: constructReferenceUrl("field", field.name)]
                            },

                    venues:
                            venues.collect { Entity venue ->
                                [id: venue.id, name: venue.name, relative_url: constructReferenceUrl("venue", venue.name)]
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
