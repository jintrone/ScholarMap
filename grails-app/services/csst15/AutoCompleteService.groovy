package csst15

import csst15.constants.EntityType
import grails.converters.JSON
import grails.transaction.Transactional

@Transactional
class AutoCompleteService {


    def loadEntity(params) {
        def results = Entity.createCriteria().list {
            if (params.type) {
                and {
                    ilike("name", "%${params.term}%")
                    eq("type", GeneralUtils.constructEntityType(params.type))
                }
            } else {
                and {
                    ilike("name", "%${params.term}%")
                    or {
                        eq("type", EntityType.FIELD)
                        eq("type", EntityType.VENUE)
                        eq("type", EntityType.THEORY)
                        eq("type", EntityType.METHOD)
                    }
                }
            }

            maxResults(Integer.parseInt(params.max, 10))
            order("name", params.order)
        }

        return results.name as JSON
    }

    def loadAuthors(params) {
        def results = Author.createCriteria().list {
            or {
                ilike("firstName", "%${params.term}%")
                ilike("lastName", "%${params.term}%")
            }

            maxResults(Integer.parseInt(params.max, 10))
            order("lastName", params.order)
        }.unique()

        results = results.collect { result ->
            result.lastName + "," + result.firstName
        }

        return results as JSON
    }
}
