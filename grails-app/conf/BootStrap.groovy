import csst15.constants.Departments
import csst15.constants.Positions
import csst15.constants.Roles
import csst15.constants.Specializations
import csst15.lists.Department
import csst15.lists.Position
import csst15.lists.Specialization
import csst15.security.Role
import csst15.security.User
import csst15.security.UserRole

class BootStrap {

    def init = { servletContext ->
        environments {
            development {
                bootstrapSpringSecurityDatabase()
                bootstrapUserDetailsDatabase()
            }
            production {
                bootstrapUserDetailsDatabase()
            }
        }
    }

    def destroy = {
    }


    private void bootstrapUserDetailsDatabase() {
        Department.withTransaction {
            Department.findByTitle(Departments.MEDIA.title) ?: new Department(title: Departments.MEDIA.title).save(failOnError: true)
            Department.findByTitle(Departments.COMM.title) ?: new Department(title: Departments.COMM.title).save(failOnError: true)
            Department.findByTitle(Departments.ADVERTISING.title) ?: new Department(title: Departments.ADVERTISING.title).save(failOnError: true)
            Department.findByTitle(Departments.JOURNALISM.title) ?: new Department(title: Departments.JOURNALISM.title).save(failOnError: true)
            Department.findByTitle(Departments.CSD.title) ?: new Department(title: Departments.CSD.title).save(failOnError: true)
        }

        Position.withTransaction {
            Position.findByName(Positions.PROFESSOR.name) ?: new Position(name: Positions.PROFESSOR.name).save(failOnError: true)
            Position.findByName(Positions.ASSOCIATE_PROFESSOR.name) ?: new Position(name: Positions.ASSOCIATE_PROFESSOR.name).save(failOnError: true)
            Position.findByName(Positions.ASSISTANT_PROFESSOR.name) ?: new Position(name: Positions.ASSISTANT_PROFESSOR.name).save(failOnError: true)
            Position.findByName(Positions.FACULTY_FIXED.name) ?: new Position(name: Positions.FACULTY_FIXED.name).save(failOnError: true)
            Position.findByName(Positions.FACULTY_CONTINUING.name) ?: new Position(name: Positions.FACULTY_CONTINUING.name).save(failOnError: true)
            Position.findByName(Positions.PHD_STUDENT.name) ?: new Position(name: Positions.PHD_STUDENT.name).save(failOnError: true)
            Position.findByName(Positions.MASTERS_STUDENT.name) ?: new Position(name: Positions.MASTERS_STUDENT.name).save(failOnError: true)
        }

        Specialization.withTransaction {
            Specialization.findByTitle(Specializations.COMPUTER_SCIENCE.title) ?: new Specialization(title: Specializations.COMPUTER_SCIENCE.title).save(failOnError: true)
            Specialization.findByTitle(Specializations.ANTHROPOLOGY.title) ?: new Specialization(title: Specializations.ANTHROPOLOGY.title).save(failOnError: true)
            Specialization.findByTitle(Specializations.SOCIOLOGY.title) ?: new Specialization(title: Specializations.SOCIOLOGY.title).save(failOnError: true)
            Specialization.findByTitle(Specializations.COMMUNICATIONS.title) ?: new Specialization(title: Specializations.COMMUNICATIONS.title).save(failOnError: true)
            Specialization.findByTitle(Specializations.ECONOMICS.title) ?: new Specialization(title: Specializations.ECONOMICS.title).save(failOnError: true)
            Specialization.findByTitle(Specializations.GEOLOGY.title) ?: new Specialization(title: Specializations.GEOLOGY.title).save(failOnError: true)
            Specialization.findByTitle(Specializations.ADVERTISING.title) ?: new Specialization(title: Specializations.ADVERTISING.title).save(failOnError: true)
            Specialization.findByTitle(Specializations.JOURNALISM.title) ?: new Specialization(title: Specializations.JOURNALISM.title).save(failOnError: true)
            Specialization.findByTitle(Specializations.PSYCHOLOGY.title) ?: new Specialization(title: Specializations.PSYCHOLOGY.title).save(failOnError: true)
            Specialization.findByTitle(Specializations.SOCIAL_PSYCHOLOGY.title) ?: new Specialization(title: Specializations.SOCIAL_PSYCHOLOGY.title).save(failOnError: true)
            Specialization.findByTitle(Specializations.INFORMATION_SCIENCE.title) ?: new Specialization(title: Specializations.INFORMATION_SCIENCE.title).save(failOnError: true)
            Specialization.findByTitle(Specializations.FEMINISM.title) ?: new Specialization(title: Specializations.FEMINISM.title).save(failOnError: true)
        }
    }

    private void bootstrapSpringSecurityDatabase() {
        def adminRole = null
        def userRole = null
        def publicRole = null
        Role.withTransaction {
            adminRole = Role.findByAuthority(Roles.ADMIN.name)
            if (!adminRole)
                adminRole = new Role(authority: Roles.ADMIN.name).save(failOnError: true)

            userRole = Role.findByAuthority(Roles.USER.name)
            if (!userRole)
                userRole = new Role(authority: Roles.USER.name).save(failOnError: true)

            publicRole = Role.findByAuthority(Roles.PUBLIC.name)
            if (!publicRole)
                publicRole = new Role(authority: Roles.PUBLIC.name).save(failOnError: true)
        }

        User admin = null
        User user = null
        User.withTransaction {
            admin = User.findByEmail("admin@example.com") ?: new User(
                    email: 'admin@example.com',
                    username: 'admin',
                    firstName: 'Admin',
                    lastName: 'Admin',
                    password: '1',
                    enabled: true
            ).save(failOnError: true)


            user = User.findByEmail("user@example.com") ?: new User(
                    email: 'user@example.com',
                    username: 'user',
                    firstName: 'User',
                    lastName: 'User',
                    password: '1',
                    enabled: true
            ).save(failOnError: true)
        }

        UserRole.withTransaction {
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
