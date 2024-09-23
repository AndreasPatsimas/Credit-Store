package gr.pwc.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InitializeException extends RuntimeException  {

    public InitializeException(String message, Exception e) {
        super(message);
    }
}
