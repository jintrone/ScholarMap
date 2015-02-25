package csst15

import csst15.conf.GeneralConf

class SecurityFilters {
    def filters = {
        signupCheck(controller: 'auth', action: 'signup') {
            before = {
                def isRegEnabled = GeneralConf.findById(1).isRegEnabled
                if (!isRegEnabled) {
                    redirect(uri: '/access-denied')
                }
            }
        }
    }
}
