package api.validators;

import api.exceptions.ValidationException;
import org.junit.Test;

/**
 * @author obondar82@gmail.com
 */
public class PhoneValidatorTest {

    @Test
    public void givenGoodPhone_whenValidated_thenOK() throws ValidationException {
        String test = "79630429597";
        ContentValidator.validate(test);
    }

    @Test(expected = ValidationException.class)
    public void givenNullPhone_whenValidated_thenException() throws ValidationException {
        String test = null;
        PhoneValidator.validate(test);
    }

    @Test(expected = ValidationException.class)
    public void givenInsufficientLenPhone_whenValidated_thenException() throws ValidationException {
        String test = "7963042959";
        PhoneValidator.validate(test);
    }

    @Test(expected = ValidationException.class)
    public void givenTooLongPhone_whenValidated_thenException() throws ValidationException {
        String test = "796304295999";
        PhoneValidator.validate(test);
    }

    @Test(expected = ValidationException.class)
    public void givenNonNumericPhone_whenValidated_thenException() throws ValidationException {
        String test = "79charchar0";
        PhoneValidator.validate(test);
    }

    @Test(expected = ValidationException.class)
    public void givenInvalidPrefixPhone_whenValidated_thenException() throws ValidationException {
        String test = "74953303030";
        PhoneValidator.validate(test);
    }

    @Test(expected = ValidationException.class)
    public void givenInvalidPrefix2Phone_whenValidated_thenException() throws ValidationException {
        String test = "18003303030";
        PhoneValidator.validate(test);
    }
}