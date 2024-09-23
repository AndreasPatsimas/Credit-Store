package gr.pwc.assignment.controllers;

import gr.pwc.assignment.dto.users.UserBalanceDTO;
import gr.pwc.assignment.dto.users.UserInfoDTO;
import gr.pwc.assignment.services.UserFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(value = "/users")
@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    /**
     * Fetch users' balances.
     *
     * @return the response entity containing the list of users' balances or a bad request status
     */
    @Operation(summary = "Fetch all Users' Balances", description = "Return a list of all Users' Balances.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users' balances",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserBalanceDTO.class)
                    )),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping(value = "/balances")
    public ResponseEntity<?> findUsersBalances() {
        log.info("Entering UserController.findUsersBalances");
        return ResponseEntity.ok(userFacade.findUsersBalances());
    }

    /**
     * Overview an existing user.
     *
     * @param userId the user's id
     * @return the response entity containing the user or a not found status
     */
    @Operation(summary = "Overview a User", description = "Return an existing user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User information",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserInfoDTO.class)
                    )),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping(value = "/info")
    public ResponseEntity<?> overview(@Valid @RequestParam UUID userId) {
        log.info("Entering UserController.findUserInfoById");
        return ResponseEntity.ok(userFacade.findUserInfoById(userId));
    }
}
