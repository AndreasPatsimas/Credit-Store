package gr.pwc.assignment.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private int errorCode;

    private HttpStatus status;

    private String message;
}
