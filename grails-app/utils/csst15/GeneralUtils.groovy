package csst15

import csst15.constants.EntityType
import org.apache.commons.lang3.text.WordUtils

import java.security.MessageDigest
import java.text.Normalizer

import static csst15.constants.EntityType.*

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

    public static String constructReferenceUrl(def prefix, String source) {
        def refUrl = Normalizer.normalize(source.toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("[^\\p{Alnum}]+", "-")
                .replace("--", "-").replace("--", "-")
                .replaceAll('[^a-z0-9]+$', "")
                .replaceAll("^[^a-z0-9]+", "")

        "/" + prefix + "/" + refUrl
    }

    public static constructOnlyParam(EntityType entityType) {
        def result = null
        switch (entityType) {
            case METHOD:
                result = "methods"
                break
            case FIELD:
                result = "fields"
                break
            case VENUE:
                result = "venues"
                break
            case THEORY:
                result = "theories"
                break
        }

        return result
    }

    public static String generateMD5(String s) {
        MessageDigest digest = MessageDigest.getInstance("MD5")
        digest.update(s.bytes);
        new BigInteger(1, digest.digest()).toString(16).padLeft(32, '0').toString()
    }
}
