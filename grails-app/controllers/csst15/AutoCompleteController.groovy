package csst15

import grails.converters.JSON

class AutoCompleteController {
    def autoCompleteService

    static allowedMethods = [
            loadEntity: 'POST'
    ]

    def loadInterests() {
        render autoCompleteService.autoComplete(params)
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
}
