package api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Common API result model
 *
 * @author obondar82@gmail.com
 */
public class ApiResult {

    /**
     * HTTP response status
     */
    private HttpStatus status;

    /**
     * Response timestamp (when new instance of the ApiResult has been created)
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    /**
     * Message
     */
    private String message;

    /**
     * Debug message
     */
    private String debug;

    public int getCode() {
        return status.value();
    }

    @JsonIgnore
    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDebug() {
        return debug;
    }

    private ApiResult() {
    }

    public ApiResult(HttpStatus status, Throwable ex) {
        this(status, "Unexpected error", ex);
    }

    public ApiResult(HttpStatus status) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
    }

    public ApiResult(HttpStatus status, String message, Throwable ex) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.debug = ex.getLocalizedMessage();
    }

    public ApiResult(HttpStatus status, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }
}
