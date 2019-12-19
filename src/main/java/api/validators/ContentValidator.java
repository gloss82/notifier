package api.validators;

import api.exceptions.ValidationException;

/**
 * Message content validator
 *
 * @author obondar82@gmail.com
 */
public class ContentValidator {

    /**
     * Validates value against internal rules
     *
     * @param value input value
     */
    public static void validate(String value) throws ValidationException {

        if (value == null) {
            // Null - no value
            throw new ValidationException("Invalid message content");
        }

        value = value.trim();

        // Check string length
        if (value.isEmpty() || value.length() > 1000) {
            // Invalid value length
            throw new ValidationException("Invalid message content length");
        }
    }
}
