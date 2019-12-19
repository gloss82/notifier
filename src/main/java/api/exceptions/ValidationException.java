package api.exceptions;

/**
 * Validation exception, throws in REST request validators
 *
 * @author obondar82@gmail.com
 */
public class ValidationException extends Exception {

    public ValidationException() {
    }

    public ValidationException(String s) {
        super(s);
    }

    public ValidationException(String s, Throwable t) {
        super(s, t);
    }

    public ValidationException(Throwable t) {
        super(t);
    }
}
