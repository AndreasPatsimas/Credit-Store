package gr.pwc.assignment.config.security;

import gr.pwc.assignment.config.MessageProcessor;
import gr.pwc.assignment.exceptions.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final MessageProcessor processor;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {

        log.error("Access denied error: {}", e.getMessage());

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        try {
            processor.handle(ErrorResponse.builder()
                    .errorCode(HttpStatus.FORBIDDEN.value())
                    .status(HttpStatus.FORBIDDEN)
                    .message("User authenticated but does not have sufficient privileges")
                    .build(), request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
