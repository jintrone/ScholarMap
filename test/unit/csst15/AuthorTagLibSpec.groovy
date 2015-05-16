package csst15

import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Specification


@TestFor(AuthorTagLib)
@Build([Author, Reference, ReferenceAuthor])
class AuthorTagLibSpec extends Specification {
    void "test author tag"() {
        given:
        def firstName1 = "Emil"
        def lastName1 = "Matevosyan"
        def firstName2 = "Joshua"
        def lastName2 = "Introne"
        def author1 = Author.build(firstName: firstName1, lastName: lastName1)
        def author2 = Author.build(firstName: firstName2, lastName: lastName2)
        def reference = Reference.build()
        def ra1 = ReferenceAuthor.build(author: author1, reference: reference)
        def ra2 = ReferenceAuthor.build(author: author2, reference: reference)
        ReferenceAuthor.metaClass.'static'.findAllByReference = { ref -> [ra1, ra2] }

        expect:
        tagLib.author() == "Matevosyan E.; Introne J.; "
    }
}
