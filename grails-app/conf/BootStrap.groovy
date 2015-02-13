import csst15.constants.Roles
import csst15.security.Role
import csst15.security.User
import csst15.security.UserRole

class BootStrap {

    def init = { servletContext ->
        environments {
            development {
                bootstrapSpringSecurityDatabase()
            }
        }
    }

    def destroy = {
    }

    private void bootstrapSpringSecurityDatabase() {
        UserRole.withTransaction {
            def adminRole = Role.findByAuthority(Roles.ADMIN.name)
            if (!adminRole)
                adminRole = new Role(authority: Roles.ADMIN.name).save(failOnError: true)

            def userRole = Role.findByAuthority(Roles.USER.name)
            if (!userRole)
                userRole = new Role(authority: Roles.USER.name).save(failOnError: true)

            def publicRole = Role.findByAuthority(Roles.PUBLIC.name)
            if (!publicRole)
                publicRole = new Role(authority: Roles.PUBLIC.name).save(failOnError: true)


            def admin = User.findByEmail("admin@example.com") ?: new User(
                    email: 'admin@example.com',
                    username: 'admin',
                    firstName: 'Admin',
                    lastName: 'Admin',
                    password: '1'
            ).save(failOnError: true)


            def user = User.findByEmail("user@example.com") ?: new User(
                    email: 'user@example.com',
                    username: 'user',
                    firstName: 'User',
                    lastName: 'User',
                    password: '1'
            ).save(failOnError: true)

            if (!admin.authorities.contains(adminRole)) {
                UserRole.create(admin, adminRole)
            }

            if (!admin.authorities.contains(userRole)) {
                UserRole.create(admin, userRole)
            }

            if (!user.authorities.contains(userRole)) {
                UserRole.create(user, userRole)
            }
        }
    }
}
