package csst15.command

import grails.validation.Validateable

/**
 * Created by Emil Matevosyan
 * Date: 3/1/15.
 */

@Validateable
class PasswordChangeCommand {
    String oldPassword
    String newPassword
    String confirmPassword

    static constraints = {
        oldPassword blank: false, size: 6..20
        newPassword blank: false, size: 6..20
        confirmPassword validator: { val, obj ->
            obj.properties['newPassword'] == val
        }
    }
}
