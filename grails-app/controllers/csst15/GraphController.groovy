package csst15

import com.google.common.base.Joiner
import csst15.constants.Roles
import csst15.security.Role
import csst15.security.User
import csst15.security.UserRole
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
                                    type: entity.type.name,
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

        render(builder.toPrettyString())
    }

    def refGraph() {
        def referencesAuthor = ReferenceAuthor.findAll()
        def referencesVote = ReferenceVote.findAll()
        def allMethods = Entity.findAllByType(METHOD)
        def allTheories = Entity.findAllByType(THEORY)
        def allVenues = Entity.findAllByType(VENUE)
        def allFields = Entity.findAllByType(FIELD)
        def users = UserRole.findAllByRole(Role.findByAuthority(Roles.USER.name))*.user.unique()

        def builder = new JsonBuilder()
        def root = builder {
            nodes(
                    referencesAuthor.reference.unique().collect { Reference reference ->
                        def authors = referencesAuthor.findAll { ReferenceAuthor refAuth -> refAuth.reference == reference }.author
                        def authorStr = Joiner.on(",").join(authors.firstName)
                        def entities = referencesVote.findAll { ReferenceVote refVote -> refVote.reference == reference }.entity
                        def methods = entities.findAll { Entity method -> method.type == METHOD }.unique()
                        def theories = entities.findAll { Entity theory -> theory.type == THEORY }.unique()
                        def fields = entities.findAll { Entity field -> field.type == FIELD }.unique()
                        def venues = entities.findAll { Entity venue -> venue.type == VENUE }.unique()
                        [
                                citation    : reference.citation,
                                year        : reference.year,
                                authors: authorStr,
                                department  : reference.creator?.department?.title ?: "",
                                relative_url: constructReferenceUrl("reference", ReferenceAuthor.findByReference(reference).author.lastName + reference.year + reference.hash),
                                methods     : (methods.id),
                                fields      : (fields.id),
                                venues      : (venues.id),
                                theories    : (theories.id)
                        ]
                    }
            )

            attributes {
                "methods" {
                    allMethods.collect { method ->
                        "${method.id}" {
                            name "${method.name}"
                            relative_url "${constructReferenceUrl(METHOD.name.toLowerCase(), method.name)}"
                        }
                    }
                }

                "theories" {
                    allTheories.collect { theory ->
                        "${theory.id}" {
                            name "${theory.name}"
                            relative_url "${constructReferenceUrl(THEORY.name.toLowerCase(), theory.name)}"
                        }
                    }
                }

                "fields" {
                    allFields.collect { field ->
                        "${field.id}" {
                            name "${field.name}"
                            relative_url "${constructReferenceUrl(FIELD.name.toLowerCase(), field.name)}"
                        }
                    }
                }

                "venues" {
                    allVenues.collect { venue ->
                        "${venue.id}" {
                            name "${venue.name}"
                            relative_url "${constructReferenceUrl(VENUE.name.toLowerCase(), venue.name)}"
                        }
                    }
                }

                "people" {
                    users.collect { user ->
                        "${user.id}" {
                            name "${user.firstName} ${user.lastName}"
                            relative_url "${constructReferenceUrl("user", user.username)}"
                        }
                    }
                }
            }
        }

        render(builder.toPrettyString())
    }

    def peopleGraph() {
        def users = UserRole.findAllByRole(Role.findByAuthority(Roles.USER.name))*.user.unique()
        def allMethods = Entity.findAllByType(METHOD)
        def allTheories = Entity.findAllByType(THEORY)
        def allVenues = Entity.findAllByType(VENUE)
        def allFields = Entity.findAllByType(FIELD)
        def allReferences = Reference.list()

        def builder = new JsonBuilder()
        def root = builder {
            nodes(
                    users.collect { User u ->
                        def userMethods = u.entities.findAll { Entity method -> method.type == METHOD }.unique()
                        def userTheories = u.entities.findAll { Entity theory -> theory.type == THEORY }.unique()
                        def userFields = u.entities.findAll { Entity field -> field.type == FIELD }.unique()
                        def userVenues = u.entities.findAll { Entity venue -> venue.type == VENUE }.unique()
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

            attributes {
                "methods" {
                    allMethods.collect { method ->
                        "${method.id}" {
                            name "${method.name}"
                            relative_url "${constructReferenceUrl(METHOD.name.toLowerCase(), method.name)}"
                        }
                    }
                }

                "theories" {
                    allTheories.collect { theory ->
                        "${theory.id}" {
                            name "${theory.name}"
                            relative_url "${constructReferenceUrl(THEORY.name.toLowerCase(), theory.name)}"
                        }
                    }
                }

                "fields" {
                    allFields.collect { field ->
                        "${field.id}" {
                            name "${field.name}"
                            relative_url "${constructReferenceUrl(FIELD.name.toLowerCase(), field.name)}"
                        }
                    }
                }

                "venues" {
                    allVenues.collect { venue ->
                        "${venue.id}" {
                            name "${venue.name}"
                            relative_url "${constructReferenceUrl(VENUE.name.toLowerCase(), venue.name)}"
                        }
                    }
                }

                "references" {
                    allReferences.collect { reference ->
                        "${reference.id}" {
                            name "${reference.citation}"
                            relative_url "${constructReferenceUrl("reference", ReferenceAuthor.findByReference(reference).author.lastName + reference.year + reference.hash)}"
                        }
                    }
                }
            }
        }


        render(builder.toPrettyString())
    }
}
