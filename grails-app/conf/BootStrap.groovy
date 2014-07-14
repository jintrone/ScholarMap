import csst15.User
import org.apache.shiro.crypto.hash.Sha256Hash

class BootStrap {

    def init = { servletContext ->
        def user = new User(username: "admin", passwordHash: new Sha256Hash("admin").toHex())
        user.addToPermissions("*:*")
        user.save(flush:true)
    }
    def destroy = {
    }
}
