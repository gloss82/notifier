package api.validators;

import api.exceptions.ValidationException;
import org.junit.Test;

import java.nio.CharBuffer;

/**
 * @author obondar82@gmail.com
 */
public class ContentValidatorTest {

    @Test
    public void givenGoodContent_whenValidated_thenOK() throws ValidationException {
        String test = "This is message!";
        ContentValidator.validate(test);
    }

    @Test(expected = ValidationException.class)
    public void givenNullContent_whenValidated_thenException() throws ValidationException {
        String test = null;
        ContentValidator.validate(test);
    }

    @Test(expected = ValidationException.class)
    public void givenEmptyContent_whenValidated_thenException() throws ValidationException {
        String test = "";
        ContentValidator.validate(test);
    }

    @Test(expected = ValidationException.class)
    public void givenSpacedContent_whenValidated_thenException() throws ValidationException {
        String test = "    ";
        ContentValidator.validate(test);
    }

    @Test(expected = ValidationException.class)
    public void givenLoooongContent_whenValidated_thenException() throws ValidationException {
        String test = CharBuffer.allocate(1025).toString().replace('\0', ' ');
        ContentValidator.validate(test);
    }
}