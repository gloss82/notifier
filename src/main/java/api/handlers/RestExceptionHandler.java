package api.handlers;

import api.exceptions.ValidationException;
import api.models.ApiResult;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Error handler implementation
 *
 * @author obondar82@gmail.com
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Malformed JSON handler
     *
     * @param ex      exception
     * @param headers http headers
     * @param status  http status
     * @param request http request
     * @return response entity
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiResult(HttpStatus.BAD_REQUEST, error, ex));
    }

    /**
     * Not found handler
     *
     * @param ex exception
     * @return response entity
     */
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            NotFoundException ex) {
        ApiResult apiResult = new ApiResult(HttpStatus.NOT_FOUND, ex.getMessage());
        return buildResponseEntity(apiResult);
    }

    /**
     * Validation failure handler
     *
     * @param ex exception
     * @return response entity
     */
    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            ValidationException ex) {
        ApiResult apiResult = new ApiResult(HttpStatus.BAD_REQUEST, ex.getMessage());
        return buildResponseEntity(apiResult);
    }

    /**
     * Common Exception handler
     *
     * @param ex exception
     * @return response entity
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            Exception ex) {
        ApiResult apiResult = new ApiResult(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        return buildResponseEntity(apiResult);
    }

    /**
     * Static response entity builder
     *
     * @param apiResult ApiResult object
     * @return response entity
     */
    private static ResponseEntity<Object> buildResponseEntity(ApiResult apiResult) {
        return new ResponseEntity<>(apiResult, apiResult.getStatus());
    }
}
