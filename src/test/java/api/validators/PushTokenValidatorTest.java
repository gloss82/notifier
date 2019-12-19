package api.validators;

import api.exceptions.ValidationException;
import org.junit.Test;

/**
 * @author obondar82@gmail.com
 */
public class PushTokenValidatorTest {

    @Test
    public void givenGoodContent_whenValidated_thenOK() throws ValidationException {
        String test = "Yoh6jocha2woo2lahdahsaijai6ohCo8Gees3aiz8AhmaeZiefarai";
        PushTokenValidator.validate(test);
    }

    @Test(expected = ValidationException.class)
    public void givenNullContent_whenValidated_thenException() throws ValidationException {
        String test = null;
        PushTokenValidator.validate(test);
    }

    @Test(expected = ValidationException.class)
    public void givenEmptyContent_whenValidated_thenException() throws ValidationException {
        String test = "";
        PushTokenValidator.validate(test);
    }

    @Test(expected = ValidationException.class)
    public void givenSpacedContent_whenValidated_thenException() throws ValidationException {
        String test = "    ";
        PushTokenValidator.validate(test);
    }
}