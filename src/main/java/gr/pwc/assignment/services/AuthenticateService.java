package gr.pwc.assignment.services;

import gr.pwc.assignment.dto.authenticate.AuthenticationRequestDTO;
import gr.pwc.assignment.dto.authenticate.AuthenticationResponseDTO;

public interface AuthenticateService {

    AuthenticationResponseDTO authenticate(AuthenticationRequestDTO authenticationRequest);
}
