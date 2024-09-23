package gr.pwc.assignment.dto.authenticate;

import gr.pwc.assignment.enums.AuthenticationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponseDTO {

    private final String jwt;
    private AuthenticationStatus authenticationStatus;
}
