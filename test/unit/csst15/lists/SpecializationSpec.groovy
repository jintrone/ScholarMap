package csst15.lists

import csst15.constants.Specializations
import csst15.utils.ConstraintsUnitSpec
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Specialization)
@Build(Specialization)
class SpecializationSpec extends ConstraintsUnitSpec {

    def setup() {
        mockForConstraintsTests(Specialization, [Specialization.build(title: Specializations.COMPUTER_SCIENCE.title)])
    }

    @Unroll("Tested Specialization field #field with the value #val")
    def "test Specialization fields' constraints"() {
        when:
        def obj = new Specialization("$field": val)

        then:
        validateConstraints(obj, field, target)

        where:
        target     | field   | val
        'nullable' | 'title' | null
        'nullable' | 'title' | getEmptyString()
        'unique'   | 'title' | Specializations.COMPUTER_SCIENCE.title
        'valid'    | 'title' | Specializations.ANTHROPOLOGY.title
    }
}
