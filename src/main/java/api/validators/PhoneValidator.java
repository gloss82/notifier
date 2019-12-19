package api.validators;

import api.exceptions.ValidationException;

import java.util.regex.Pattern;

/**
 * Phone number validator
 *
 * @author obondar82@gmail.com
 */
public class PhoneValidator {

    // Valid phone number regexp pattern
    final static Pattern pattern = Pattern.compile("^79\\d{9}$");

    /**
     * Validates value against internal rules
     *
     * @param value input value
     */
    public static void validate(String value) throws ValidationException {

        if (value == null) {
            // Null - no value
            throw new ValidationException("Invalid phone number");
        }

        // Check string length
        if (value.length() != 11) {
            // Invalid value length
            throw new ValidationException("Invalid phone number");
        }

        // Match against pattern
        if (!pattern.matcher(value).matches()) {
            // Invalid value content - must start with 7 and contain exactly 11 digits
            throw new ValidationException("Invalid phone number");
        }
    }
}
