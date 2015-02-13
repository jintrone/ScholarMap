package csst15.utils

import spock.lang.Specification

/**
 * Created by Emil Matevosyan
 * Date: 2/13/15.
 */
class ConstraintsUnitSpec extends Specification {
    String getCustomTextWithLength(Integer length) {
        'a' * length
    }

    String getEmptyString() {
        ''
    }

    void validateConstraints(obj, field, target) {
        def validated = obj.validate()
        if (target && target != 'valid') {
            assert !validated
            assert obj.errors[field]
            assert target == obj.errors[field]
        } else {
            assert !obj.errors[field]
        }
    }
}
