package gr.pwc.assignment.controllers;

import gr.pwc.assignment.dto.authenticate.AuthenticationRequestDTO;
import gr.pwc.assignment.services.AuthenticateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticateService authenticateService;

    @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createAuthenticateToken(@RequestBody AuthenticationRequestDTO authenticationRequest) {

        log.info("Authenticate user {}", authenticationRequest.getUsername());

        return ResponseEntity.ok(authenticateService.authenticate(authenticationRequest));
    }
}
