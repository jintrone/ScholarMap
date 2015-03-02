package csst15.command

import csst15.conf.FieldMandatoryConf
import grails.validation.Validateable

/**
 * Created by Emil Matevosyan
 * Date: 2/28/15.
 */

@Validateable
class QuickNewUserCommand {
    String username
    String email
    String password
    String confirmPassword
    String firstName
    String lastName

    static constraints = {
        username blank: false, unique: true
        email email: true, blank: false, unique: true
        password blank: false, size: 6..20
        confirmPassword validator: { val, obj ->
            obj.properties['password'] == val
        }
        firstName nullable: true, validator: { val, obj ->
            FieldMandatoryConf.withNewSession { session ->
                if (FieldMandatoryConf.findByFieldName('firstName')?.isMandatory && !val) {
                    return ['firstName.required']
                }
            }
        }
        lastName nullable: true, validator: { val, obj ->
            FieldMandatoryConf.withNewSession { session ->
                if (FieldMandatoryConf.findByFieldName('lastName')?.isMandatory && !val)
                    return ['lastName.required']
            }
        }
    }
}
