package csst15

import org.apache.commons.lang3.text.WordUtils

import java.text.Normalizer

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

    public static String constructReferenceUrl(String prefix, String source) {
        def refUrl = Normalizer.normalize(source.toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("[^\\p{Alnum}]+", "-")
                .replace("--", "-").replace("--", "-")
                .replaceAll('[^a-z0-9]+$', "")
                .replaceAll("^[^a-z0-9]+", "")

        "/" + prefix + "/" + refUrl
    }

    public static constructOnlyParam(String entityType) {
        def result = null
        switch (entityType) {
            case "method":
                result = "methods"
                break
            case "field":
                result = "fields"
                break
            case "venue":
                result = "venues"
                break
            case "theory":
                result = "theories"
                break
        }

        return result
    }
}
