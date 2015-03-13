package csst15.security

import csst15.Entity
import csst15.ReferenceVote
import csst15.conf.FieldLockConf
import csst15.conf.FieldVisibilityConf
import csst15.lists.Department
import csst15.lists.Position
import csst15.lists.Specialization
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
    Specialization specialization
    Position position
    Department department
    byte[] photo
    boolean enabled
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    // holds the activation token that will be embedded in the activation link, sent to a new user
    String activationToken
    String passwordResetToken

    static transients = ['springSecurityService']

    static hasMany = [references: ReferenceVote, entities: Entity]
    static hasOne = [lockConf: FieldLockConf, visibilityConf: FieldVisibilityConf]


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
        photo nullable: true, maxSize: 3 * 1024 * 1024
        activationToken nullable: true
        passwordResetToken nullable: true
    }

    static mapping = {
        entities cascade: 'all-delete-orphan'
        references cascade: 'all-delete-orphan'
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
