package gr.pwc.assignment.controllers;

import gr.pwc.assignment.dto.users.EditUserBalanceDTO;
import gr.pwc.assignment.dto.users.UserBalanceDiffDTO;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequestMapping(value = "/user-balances")
@RestController
@Slf4j
@RequiredArgsConstructor
public class UserBalanceController {

    private final UserFacade userFacade;

    /**
     * Modify user's balance.
     *
     * @param editUserBalance request data in order to edit user's balance
     * @return the response entity containing the difference between old and new user's balance
     */
    @Operation(summary = "Modify User's balance", description = "Return difference between old and new user's balance.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modification of User's balance is valid",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserBalanceDiffDTO.class)
                    )),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(mediaType = "application/json"))
    })
    @PatchMapping
    public ResponseEntity<?> modify(@Valid @RequestBody EditUserBalanceDTO editUserBalance, Principal principal) {
        log.info("Entering UserBalanceController.modify");
        return ResponseEntity.ok(userFacade.modifyUserBalance(editUserBalance, principal.getName()));
    }
}
