package csst15

import csst15.constants.Roles
import csst15.security.Role
import csst15.security.User
import csst15.security.UserRole
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import grails.util.Holders
import grails.validation.ValidationException
import groovy.util.logging.Slf4j

@Slf4j
@Transactional(readOnly = true)
class UserController {
    def springSecurityService

    static allowedMethods = [signup: 'GET']


    @Secured('permitAll')
    def signup() {
        render(view: '/user/signup')
    }

    @Transactional
    def register() {
        def user = new User(params)
        def isRolledback = false

        boolean passwordsMatch = params.password == params.rpassword
        user.enabled = true
        User.withTransaction { status ->
            try {
                if (!passwordsMatch)
                    render(view: '/user/signup')
                user.save(failOnError: true)
                log.info("Registered user ${user.email} successfully with id ${user.id}.")
                if (Holders.config.grails.email.skip) {
                    log.debug "skip email confirmation step. active user"
                } else {
//                        TODO: send email
                }
            } catch (e) {
                status.setRollbackOnly()
                isRolledback = true
                log.error("Could not save the user with username ${user.username}")
                log.error("Details of user which has failed to register :\n${user.toString()} ")
                if (!(e instanceof ValidationException))
                    flash.error = e.message
                render(view: '/user/signup', model: [userInstance: user, exception: e])
            }

            if (!isRolledback) {
                UserRole.create(user, Role.findByAuthority(Roles.USER.name), true)
                springSecurityService.reauthenticate(user.username)
                redirect(controller: 'home')
            }
        }
    }

//    def index() {}
//
//    def register = {
//
//        return [email: params.email, nologin: true]
//    }
//
//    def doRegister = {
//
//        if (!params.email || !params.password1) {
//            flash.message = "Both email and password are required to create an account"
//            return redirect(action: "register", params: [email: params.email])
//        }
//
//        if (User.findByEmail(params.email)) {
//            flash.message = "This email address is already registered; please click \"forgot my login\" if you can't remember your password."
//            return redirect(action: "register", params: [email: params.email])
//
//        }
//
//        if (params.password1 != params.password2) {
//            flash.message = "Passwords did not match"
//            return redirect(action: "register", params: [email: params.email])
//        }
//
//        User u = new User(email: params.email, firstName: params.firstName, lastName: params.lastName, passwordHash: new Sha256Hash(params.password1).toHex(),
//                position: params.position, institution: params.institution)
//        u.addToPermissions("*:*")
//        u.save(flush: true)
//
//
//
//        if (u.hasErrors()) {
//
//            flash.message = u.getErrors() as String
//            return redirect(actions: "register", params: [email: params.email])
//
//        }
//
//
//
//        def authToken = new UsernamePasswordToken(params.email, params.password1 as String)
//
//        try {
//            // Perform the actual login. An AuthenticationException
//            // will be thrown if the username is unrecognised or the
//            // password is incorrect.
//            SecurityUtils.subject.login(authToken)
//            return redirect(action: "profile")
//        }
//        catch (AuthenticationException ex) {
//
//            // Authentication failed, so display the appropriate message
//            // on the login page.
//            println "Login failed!"
//            log.info "Authentication failure for user '${params.email}'."
//            flash.message = message(code: "login.failed")
//
//            // Keep the username and "remember me" setting so that the
//            // user doesn't have to enter them again.
//            def m = [email: params.email]
//            if (params.rememberMe) {
//                m["rememberMe"] = true
//            }
//
//            // Remember the target URI too.
//            if (params.targetUri) {
//                m["targetUri"] = params.targetUri
//            }
//
//            // Now redirect back to the login page.
//            flash.message = "Login failed; please contact Josh at jintrone@msu.com"
//
//        }
//        redirect(uri: "/")
//    }
//
//
//    def profile = {
//        return [user: User.findByEmail(SecurityUtils.subject.principal),tab:params.tab, page:"profile"]
//    }
//
//
//    def entitiesByType = {
//        Class c = Entity.resolve(params.type)
//        User u = User.findByEmail(SecurityUtils.subject.principal)
//        render(template:"/common/userentities",model:[type:params.type,data:u.getEntitiesOfType(c)])
//    }
//    def setprefs = {
//        User u = User.findByEmail(SecurityUtils.subject.principal)
//        def prefs = u.getPrefs()
//        prefs[params.pref as String] = (params.value == "on"?"false":"true")
//        u.setPrefs(prefs)
//        u.save(flush:true)
//        render("Success")
//    }


}

