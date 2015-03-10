import csst15.*
import csst15.conf.FieldLockConf
import csst15.conf.FieldMandatoryConf
import csst15.conf.FieldVisibilityConf
import csst15.conf.GeneralConf
import csst15.constants.*
import csst15.lists.Department
import csst15.lists.Position
import csst15.lists.Specialization
import csst15.security.Role
import csst15.security.User
import csst15.security.UserRole
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass

class BootStrap {

    def init = { servletContext ->
        environments {
            development {
                bootstrapUserDetailsDatabase()
                bootstrapSpringSecurityDatabase()
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

        GeneralConf.withTransaction {
            GeneralConf.get(1) ?: new GeneralConf().save(failOnError: true)
        }

        FieldMandatoryConf.withNewTransaction {
            def nonUserInput = ["username", "password", "email", "photo", "activationToken", "accountLocked", "lockConf",
                                "accountExpired", "enabled", "entities", "passwordExpired", "passwordResetToken", "visibilityConf"]
            def userDomainClass = new DefaultGrailsDomainClass(User.class)
            userDomainClass.persistentProperties.each { field ->
                if (!nonUserInput.contains(field.name)) {
                    FieldMandatoryConf.findByFieldName(field.name as String) ?: new FieldMandatoryConf(fieldName: field.name, isMandatory: false).save(failOnError: true)
                }
            }
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
            def lockConf1 = new FieldLockConf()
            def lockConf2 = new FieldLockConf()
            def visConf1 = new FieldVisibilityConf()
            def visConf2 = new FieldVisibilityConf()
            def mandConf1 = new FieldMandatoryConf()
            def mandConf2 = new FieldMandatoryConf()

            admin = User.findByEmail("admin@example.com") ?: new User(
                    email: 'admin@example.com',
                    username: 'admin',
                    firstName: 'Admin',
                    lastName: 'Admin',
                    password: '1',
                    enabled: true,
                    lockConf: lockConf1,
                    visibilityConf: visConf1,
            ).save(failOnError: true)

            user = User.findByEmail("user@example.com") ?: new User(
                    email: 'user@example.com',
                    username: 'user',
                    firstName: 'User',
                    lastName: 'User',
                    password: '1',
                    enabled: true,
                    lockConf: lockConf2,
                    visibilityConf: visConf2,
            ).save(failOnError: true)
        }

        UserRole.withTransaction {
            if (!admin.authorities.contains(adminRole)) {
                UserRole.create(admin, adminRole)
            }

            if (!user.authorities.contains(userRole)) {
                UserRole.create(user, userRole)
            }
        }

        def field1 = null
        def field2 = null
        def method = null
        def theory1 = null
        def theory2 = null
        def theory3 = null
        Entity.withTransaction {
            method = Entity.findByName("User-Centered Design") ?: new Method(name: 'User-Centered Design', description: 'bla bla', type: EntityType.METHOD).save(failOnError: true)
            theory1 = Entity.findByName("Cognitive Anthropology") ?: new Theory(name: 'Cognitive Anthropology', description: 'bla bla', type: EntityType.THEORY).save(failOnError: true)
            theory2 = Entity.findByName("Critical Theory") ?: new Theory(name: 'Critical Theory', description: 'bla bla', type: EntityType.THEORY).save(failOnError: true)
            theory3 = Entity.findByName("Cognitive Artifacts") ?: new Theory(name: 'Cognitive Artifacts', description: 'bla bla', type: EntityType.THEORY).save(failOnError: true)
            field1 = Entity.findByName("Human Computer Interaction") ?: new Field(name: 'Human Computer Interaction', description: 'bla bla', type: EntityType.FIELD).save(failOnError: true)
            field2 = Entity.findByName("Design") ?: new Field(name: 'Design', description: 'bla bla', type: EntityType.FIELD).save(failOnError: true)

            user.addToEntities(method)
            user.addToEntities(theory1)
            user.addToEntities(theory2)
            user.addToEntities(theory3)
            user.addToEntities(field1)
            user.addToEntities(field2)
        }

        def reference = null
        def reference1 = null
        Reference.withTransaction {
            reference = Reference.findByCitation("Traditional") ?: new Reference(citation: 'Traditional', content: 'bla bla', year: 2014, creator: user).save(failOnError: true)
            reference1 = Reference.findByCitation("Another Ref") ?: new Reference(citation: 'Another Ref', content: 'bla bla', year: 2013, creator: user).save(failOnError: true)

            new ReferenceVote(reference: reference, user: user, entity: field1).save(failOnError: true)
            new ReferenceVote(reference: reference, user: user, entity: theory1).save(failOnError: true)
            new ReferenceVote(reference: reference, user: user, entity: theory2).save(failOnError: true)
            new ReferenceVote(reference: reference, user: user, entity: theory3).save(failOnError: true)
            new ReferenceVote(reference: reference1, user: user, entity: theory2).save(failOnError: true)
            new ReferenceVote(reference: reference1, user: user, entity: theory3).save(failOnError: true)
            new ReferenceVote(reference: reference1, user: user, entity: field2).save(failOnError: true)
        }

        ReferenceAuthor.withTransaction {
            def author = Author.findById(1) ?: new Author(firstName: 'Emil', lastName: 'Matevosyan').save(failOnError: true)
            def author2 = Author.findById(2) ?: new Author(firstName: 'Joshua', lastName: 'Introne').save(failOnError: true)
            def author3 = Author.findById(3) ?: new Author(firstName: 'Joe', lastName: 'Doe').save(failOnError: true)

            new ReferenceAuthor(author: author, reference: reference).save(failOnError: true)
            new ReferenceAuthor(author: author2, reference: reference).save(failOnError: true)
            new ReferenceAuthor(author: author3, reference: reference).save(failOnError: true)
            new ReferenceAuthor(reference: reference1, author: author3).save(failOnError: true)
        }
    }
}
