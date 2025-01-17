package gr.pwc.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class UnacceptableActionException extends RuntimeException {

    public UnacceptableActionException(String message) {
        super(message);
    }

    public UnacceptableActionException(String message, Throwable cause) {
        super(message, cause);
    }
}
