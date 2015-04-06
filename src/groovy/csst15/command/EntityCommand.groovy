package csst15.command

import grails.validation.Validateable

/**
 * Created by Emil Matevosyan
 * Date: 3/11/15.
 */

@Validateable
class EntityCommand {
    String name
    String description

    static constraints = {
        name blank: false, unique: true
        description blank: false, maxSize: 5000
    }
}
