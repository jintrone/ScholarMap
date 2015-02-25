package csst15

import org.apache.commons.lang3.text.WordUtils

/**
 * Created by Emil Matevosyan
 * Date: 2/24/15.
 */

public final class GeneralUtils {
    private static final String LOCKED_SUFFIX = 'Locked'
    private static final String MANDATORY_SUFFIX = 'Mandatory'
    private static final String VISIBLE_SUFFIX = 'Visible'
    private static final String PREFIX = 'is'

    private GeneralUtils() {
    }

    public static String constructFieldForLock(String fieldName) {
        return PREFIX.concat(WordUtils.capitalize(fieldName)).concat(LOCKED_SUFFIX)
    }

    public static String constructMandatoryField(String fieldName) {
        return PREFIX.concat(WordUtils.capitalize(fieldName)).concat(MANDATORY_SUFFIX)
    }

    public static String constructVisibleField(String fieldName) {
        return PREFIX.concat(WordUtils.capitalize(fieldName)).concat(VISIBLE_SUFFIX)
    }
}
