package csst15.command

import grails.validation.Validateable

/**
 * Created by Emil Matevosyan
 * Date: 2/28/15.
 */

@Validateable
class EmailOnlyCommand {
    String email

    static constraints = {
        email nullable: false, blank: false, email: true
    }
}
