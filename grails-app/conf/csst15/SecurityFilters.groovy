package csst15

import csst15.conf.FieldMandatoryConf
import csst15.conf.GeneralConf
import csst15.constants.Roles
import csst15.security.User

class SecurityFilters {
    def springSecurityService

    def filters = {
        signupCheck(controller: 'auth', action: 'signup') {
            before = {
                def isRegEnabled = GeneralConf.get(1).isRegEnabled
                if (!isRegEnabled) {
                    redirect(uri: '/access-denied')
                }
            }
        }

        fillRequired(controller: 'home', action: 'index') {
            after = {
                def currentUser = springSecurityService.currentUser as User

                if (currentUser && !currentUser.authorities.contains(Roles.ADMIN.name)) {
                    def fields = FieldMandatoryConf.list()

                    for (def field : fields) {
                        if (field.isMandatory && !currentUser."${field.fieldName}") {
                            flash.isRequired = true
                            redirect(controller: 'user', action: 'fillRequiredFields')
                            break
                        }
                    }
                }
            }
        }
    }
}
