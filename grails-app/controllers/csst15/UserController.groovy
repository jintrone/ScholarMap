package csst15

import grails.converters.JSON
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.crypto.hash.Sha256Hash

class UserController {

    def index() {}

    def register = {

        return [email: params.email, nologin: true]
    }

    def doRegister = {

        if (!params.email || !params.password1) {
            flash.message = "Both email and password are required to create an account"
            return redirect(action: "register", params: [email: params.email])
        }

        if (User.findByEmail(params.email)) {
            flash.message = "This email address is already registered; please click \"forgot my login\" if you can't remember your password."
            return redirect(action: "register", params: [email: params.email])

        }

        if (params.password1 != params.password2) {
            flash.message = "Passwords did not match"
            return redirect(action: "register", params: [email: params.email])
        }

        User u = new User(email: params.email, firstName: params.firstName, lastName: params.lastName, passwordHash: new Sha256Hash(params.password1).toHex(),
                position: params.position, institution: params.institution)
        u.addToPermissions("*:*")
        u.save(flush: true)



        if (u.hasErrors()) {

            flash.message = u.getErrors() as String
            return redirect(actions: "register", params: [email: params.email])

        }



        def authToken = new UsernamePasswordToken(params.email, params.password1 as String)

        try {
            // Perform the actual login. An AuthenticationException
            // will be thrown if the username is unrecognised or the
            // password is incorrect.
            SecurityUtils.subject.login(authToken)
            return redirect(action: "profile")
        }
        catch (AuthenticationException ex) {

            // Authentication failed, so display the appropriate message
            // on the login page.
            println "Login failed!"
            log.info "Authentication failure for user '${params.email}'."
            flash.message = message(code: "login.failed")

            // Keep the username and "remember me" setting so that the
            // user doesn't have to enter them again.
            def m = [email: params.email]
            if (params.rememberMe) {
                m["rememberMe"] = true
            }

            // Remember the target URI too.
            if (params.targetUri) {
                m["targetUri"] = params.targetUri
            }

            // Now redirect back to the login page.
            flash.message = "Login failed; please contact Josh at jintrone@msu.com"

        }
        redirect(uri: "/")
    }


    def profile = {
        return [user: User.findByEmail(SecurityUtils.subject.principal),tab:params.tab, page:"profile"]
    }


    def entitiesByType = {
        Class c = Entity.resolve(params.type)
        User u = User.findByEmail(SecurityUtils.subject.principal)
        render(template:"/common/userentities",model:[type:params.type,data:u.getEntitiesOfType(c)])
    }
    def setprefs = {
        User u = User.findByEmail(SecurityUtils.subject.principal)
        def prefs = u.getPrefs()
        prefs[params.pref as String] = (params.value == "on"?"false":"true")
        u.setPrefs(prefs)
        u.save(flush:true)
        render("Success")
    }


}

