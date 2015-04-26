package csst15

import csst15.constants.EntityType
import grails.converters.JSON
import grails.transaction.Transactional

@Transactional
class AutoCompleteService {


    def autoComplete(params) {
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
}
