package gr.pwc.assignment.services.impl;

import gr.pwc.assignment.config.security.JwtTokenProvider;
import gr.pwc.assignment.dto.authenticate.AuthenticationRequestDTO;
import gr.pwc.assignment.dto.authenticate.AuthenticationResponseDTO;
import gr.pwc.assignment.enums.AuthenticationStatus;
import gr.pwc.assignment.exceptions.AuthenticationFailedException;
import gr.pwc.assignment.services.AuthenticateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticateServiceImpl implements AuthenticateService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO authenticationRequest) {

        authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                authenticationRequest.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenProvider.generateToken(new HashMap<>(), userDetails.getUsername());
        return new AuthenticationResponseDTO(jwt, AuthenticationStatus.AUTHENTICATION_SUCCEEDED);
    }

    private void authenticate(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (BadCredentialsException e) {
            throw new AuthenticationFailedException(e.getMessage());
        }
    }
}
