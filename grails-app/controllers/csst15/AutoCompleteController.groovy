package csst15

import grails.converters.JSON
import org.apache.commons.lang3.StringUtils

class AutoCompleteController {
    def autoCompleteService

    static allowedMethods = [
            loadEntity: 'POST'
    ]

    def loadInterests() {
        render autoCompleteService.loadEntity(params)
    }

    def loadEntity() {
        def entity = Entity.findByName(params.name)

        entity = entity.collect {
            [
                    type: entity.type.name,
                    name: entity.name,
                    desc: entity.description
            ]
        }
        render(entity as JSON)
    }

    def loadAuthors() {
        render autoCompleteService.loadAuthors(params)
    }

    def loadAuthorRefs() {
        def authorName = StringUtils.split(params.name, ',')
        def author = Author.findByFirstNameAndLastName(authorName[1].trim(), authorName[0].trim())
        def references = ReferenceAuthor.findAllByAuthor(author).reference.unique()

        references = references.collect { reference ->
            [
                    citation: reference.citation,
                    id      : reference.id
            ]
        }

        render(references as JSON)
    }

    def loadRefAuthorDetails() {
        if (params.id) {
            def reference = Reference.findById(params.id)
            def refAuthor = ReferenceAuthor.findByReference(reference)

            def results = refAuthor.collect { result ->
                [
                        citation: result.reference.citation,
                        year    : result.reference.year
                ]
            }

            render(results as JSON)
        } else {
            redirect(uri: '/not-found')
        }
    }
}
