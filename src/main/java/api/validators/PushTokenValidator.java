package api.validators;

import api.exceptions.ValidationException;

/**
 * Push token validator
 *
 * @author obondar82@gmail.com
 */
public class PushTokenValidator {

    /**
     * Validates value against internal rules
     *
     * @param value input value
     */
    public static void validate(String value) throws ValidationException {

        if (value == null) {
            // Null - no value
            throw new ValidationException("Invalid push token");
        }

        // Check string length
        if (value.trim().isEmpty()) {
            // Invalid value length
            throw new ValidationException("Invalid message token length");
        }
    }
}
