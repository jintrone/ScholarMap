package csst15.command

import grails.validation.Validateable

/**
 * Created by Emil Matevosyan
 * Date: 3/11/15.
 */

@Validateable
class ReferenceCommand {
    String year
    String citation
    String fullName

    static constraints = {
        citation blank: false, maxSize: 500
        fullName blank: false
    }
}
