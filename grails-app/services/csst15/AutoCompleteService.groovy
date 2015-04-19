package csst15

import grails.converters.JSON
import grails.transaction.Transactional

@Transactional
class AutoCompleteService {


    def autoComplete(params) {
        def results = Entity.createCriteria().list {
            ilike("name", "%${params.term}%")
            maxResults(Integer.parseInt(params.max, 10))
            order("name", params.order)
        }

        return results.name as JSON
    }
}
