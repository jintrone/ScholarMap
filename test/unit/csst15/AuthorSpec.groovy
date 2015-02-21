package csst15

import csst15.utils.ConstraintsUnitSpec
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Unroll

@TestFor(Author)
@Build(Author)
class AuthorSpec extends ConstraintsUnitSpec {

    def setup() {
        mockForConstraintsTests(Author, [Author.build(firstName: 'firstName', lastName: 'lastName')])
    }

    @Unroll("Tested Author field #field with the value #val")
    def "test Author fields' constraints"() {
        when:
        def obj = new Author("$field": val)

        then:
        validateConstraints(obj, field, target)

        where:
        target     | field       | val
        'nullable' | 'firstName' | null
        'nullable' | 'firstName' | getEmptyString()
        'valid'    | 'firstName' | getCustomTextWithLength(5)
        'nullable' | 'lastName'  | null
        'nullable' | 'lastName'  | getEmptyString()
        'valid'    | 'lastName'  | getCustomTextWithLength(5)
    }
}
