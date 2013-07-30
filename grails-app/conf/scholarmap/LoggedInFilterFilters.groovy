package scholarmap

class LoggedInFilterFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
                if (!session.user && !(controllerName=="default" || (controllerName=="user" && actionName=="handleLogin"))) {
                    flash.message = "You will need to login before to access other parts of the application"
                    redirect(action:"index",controller:"default")
                    return false
                } else if (session.user && controllerName=="default") {
                    redirect(controller:"theory",action:"vote")
                    return false
                }
                true
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
