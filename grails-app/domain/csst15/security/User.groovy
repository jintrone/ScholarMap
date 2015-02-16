package csst15.security

import csst15.Entity
import csst15.ReferenceVote
import org.joda.time.LocalDate

class User {

    transient springSecurityService

    String username
    String firstName
    String lastName
    String email
    String password
    Integer degreeYear
    String institution
    String specialization
    String position
    String department
    byte[] photo
    boolean enabled
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    static transients = ['springSecurityService']

    static hasMany = [references: ReferenceVote, entities: Entity]


    static constraints = {
        username blank: false, unique: true
        password blank: false
        firstName nullable: true
        lastName nullable: true
        email nullable: false, blank: false, unique: true, email: true
        institution nullable: true
        specialization nullable: true
        position nullable: true
        department nullable: true
        degreeYear nullable: true, max: LocalDate.now().year
        photo nullable: true
    }

    static mapping = {
        password column: '`password`'
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role }
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }
}
