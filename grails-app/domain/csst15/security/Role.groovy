package csst15.security

class Role {

    String authority

    static mapping = {
        cache true
    }

//	static hasMany = [users: csst15.User, permissions: String]

    static constraints = {
        authority blank: false, unique: true
    }
}
