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
import grails.util.Holders
import groovy.sql.Sql
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass

class BootStrap {
    def grailsApplication
    def dataSource

    def init = { servletContext ->
        environments {
            development {
                bootstrapUserDetailsDatabase()
                bootstrapSpringSecurityDatabase()
               // bootstrapFullSampleData()
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

            UserEntity.create(user, method)
            UserEntity.create(user, theory1)
            UserEntity.create(user, theory2)
            UserEntity.create(user, theory3)
            UserEntity.create(user, field1)
            UserEntity.create(user, field2)
        }

        def reference = null
        def reference1 = null
        Reference.withTransaction {
            reference = Reference.findByCitation("Traditional") ?: new Reference(citation: 'Traditional', content: 'bla bla', year: 2014, creator: user, hash: GeneralUtils.generateMD5("Traditional")).save(failOnError: true)
            reference1 = Reference.findByCitation("Another Ref") ?: new Reference(citation: 'Another Ref', content: 'bla bla', year: 2013, creator: user, hash: GeneralUtils.generateMD5("Another Ref")).save(failOnError: true)

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
            def author3 = Author.findById(3) ?: new Author(firstName: 'John', lastName: 'Doe').save(failOnError: true)

            new ReferenceAuthor(author: author, reference: reference, authorOrder: 1).save(failOnError: true)
            new ReferenceAuthor(author: author2, reference: reference, authorOrder: 2).save(failOnError: true)
            new ReferenceAuthor(author: author3, reference: reference, authorOrder: 3).save(failOnError: true)
            new ReferenceAuthor(reference: reference1, author: author3, authorOrder: 1).save(failOnError: true)
        }
    }

    private void addEntity(String name, String description, EntityType type) {
        switch(type) {

            case EntityType.FIELD:
                Entity.findByName(name) ?: new Field(name: name, description: description, type: type).save(failOnError: true)
                break

            case EntityType.METHOD:
                Entity.findByName(name) ?: new Method(name: name, description: description, type: type).save(failOnError: true)
                break
            case EntityType.VENUE:
                Entity.findByName(name) ?: new Venue(name: name, description: description, type: type, kind: "brouhaha").save(failOnError: true)
                break
            case EntityType.THEORY:
                Entity.findByName(name) ?: new Theory(name: name, description: description, type: type).save(failOnError: true)
                break
        }
    }

    private User addUser(String first, String last) {
        String username = (first+last).toLowerCase()
        String email = username+"@example.com"
        User.findByEmail(email) ?: new User(
                email: email,
                username: username,
                firstName: first,
                lastName: last,
                password: '1',
                enabled: true,
                lockConf: new FieldLockConf(),
                visibilityConf: new FieldVisibilityConf()
        ).save(failOnError: true)

    }

    private addReference(List authors, String title, String content, int year) {
        List a = authors.collect { author ->
            ReferenceAuthor.withTransaction {
                Author.findByFirstNameAndLastName(author[0], author[1]) ?: new Author(firstName: author[0], lastName: author[1]).save(failOnError: true)
            }
        }
        User user = User.findByEmail("user@example.com")

        Reference r = Reference.withTransaction {
            Reference.findByYearAndCitation(year, title) ?: new Reference(citation: title, content: content, year: year, creator: user, hash: GeneralUtils.generateMD5(title)).save(failOnError: true)
        }

        int count = ReferenceAuthor.findAllByReference(r).size()

        ReferenceAuthor.withTransaction {
            a.each { authorToAdd ->
                ReferenceAuthor.where {
                    author == authorToAdd && reference == r
                }.find()?:new ReferenceAuthor(author: authorToAdd, reference: r, authorOrder: (++count)).save(failOnError: true)
            }
        }




    }


    private void bootstrapFullSampleData() {

        List<String> termDictionary = new File("./words.csv").readLines()
        List<String> descriptionDictionary = []+termDictionary
        List<String> users = new File("./MOCK_USERS.csv").readLines()

        Random rnd = new Random()
        (0..<1200).each {
            String name= (1..3).collect {
                int x = rnd.nextInt(termDictionary.size())
                termDictionary.remove(x)
            }.join(" ")

            String desc= (1..(50+rnd.nextInt(150))).collect {
                descriptionDictionary.get(rnd.nextInt(descriptionDictionary.size()))
            }.join(" ")


            EntityType type = (EntityType.values() as List).get(rnd.nextInt(4))

            addEntity(name,desc,type)

        }

        new File("./MOCK_REFS.csv").eachLine { String line->
            def tokens = line.split(",")
            List authors = (0..<(rnd.nextInt(6)+1)).collect {
                String[] s = users.get(rnd.nextInt(users.size())).split(",")
                [s[0],s[1]] as String[]
            }
            addReference(authors,tokens[0],tokens[1],tokens[2] as int)
        }

        int maxEntity = Entity.count()
        int maxRefs = Reference.count()


        users.each { String line ->
            def tokens = line.split(",")
            User u = addUser(tokens[0], tokens[1])
            u.setDegreeYear(1980)
            u.setDepartment(Department.get(tokens[3] as Integer))
            u.setSpecialization(Specialization.get(tokens[2] as Integer))
            u.setPosition(Position.get(tokens[4] as Integer))
            u.save(failOnError: true)

            (0..<(5 + rnd.nextInt(16))).each {
                (rnd.nextInt(maxEntity) + 1).each {
                    Entity e = Entity.get(it)
                    UserEntity.create(u,e)

                    (0..rnd.nextInt(5)).each {
                        List<ReferenceVote> votes = ReferenceVote.findAllByEntity(e)
                        if (rnd.nextBoolean() || !votes) {
                            (rnd.nextInt(maxRefs) + 1).each {
                                Reference ref = Reference.get(it)
                                ReferenceVote.findByReferenceAndEntity(ref, e) ?: new ReferenceVote(reference: ref, user: u, entity: e).save(failOnError: true)
                            }
                        } else {

                            Reference ref = votes.get(rnd.nextInt(votes.size())).reference
                            if (ReferenceVote.countByUserAndEntityAndReference(u, e, ref) == 0) {
                                new ReferenceVote(reference: ref, user: u, entity: e).save(failOnError: true)
                            }

                        }
                    }
                }
            }


        }
    }


    private void executeSqlScript(String filePath) {
        Sql sql = new Sql(dataSource)
        try {
            if (Holders.config.dataSource.dbCreate.equals('create')) {
                String sqlFilePath = grailsApplication.parentContext.servletContext.getRealPath(filePath)
                new File(sqlFilePath).eachLine {
                    println it
                    sql.execute(it)
                }
            }

        } finally {
            sql.close()
        }
    }
}
