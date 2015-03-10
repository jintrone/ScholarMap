package csst15.command

import grails.validation.Validateable

/**
 * Created by Emil Matevosyan
 * Date: 3/11/15.
 */

@Validateable
class ReferenceCommand {
    String year
    String content
    String citation

    static constraints = {
        citation blank: false, maxSize: 500
        content blank: false, maxSize: 5000
    }
}
