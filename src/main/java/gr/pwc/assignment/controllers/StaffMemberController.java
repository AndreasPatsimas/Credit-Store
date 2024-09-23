package gr.pwc.assignment.controllers;

import gr.pwc.assignment.dto.staff_members.StaffMemberDTO;
import gr.pwc.assignment.dto.staff_members.StaffMemberRequestDTO;
import gr.pwc.assignment.services.StaffMemberFacade;
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

import java.security.Principal;

@RequestMapping(value = "/staff-members")
@RestController
@Slf4j
@RequiredArgsConstructor
public class StaffMemberController {

    private final StaffMemberFacade staffMemberFacade;

    /**
     * Add Staff Member.
     *
     * @param staffMemberRequest request data in order to add a staff-member
     * @return the response entity containing the new staff-member
     */
    @Operation(summary = "Add Staff Member", description = "Return new Staff Member.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Add new Staff Member",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StaffMemberDTO.class)
                    )),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping(value = "/add")
    public ResponseEntity<?> add(@Valid @RequestBody StaffMemberRequestDTO staffMemberRequest, Principal principal) {
        log.info("Entering StaffMemberController.add");
        return ResponseEntity.ok(staffMemberFacade.addStaffMember(staffMemberRequest, principal.getName()));
    }

    /**
     * "Remove Staff Member By Id
     *
     * @param staffMemberId the staff member's id
     * @return the response entity containing successful response of deletion or a bad request status
     */
    @Operation(summary = "Remove Staff Member By Id", description = "Remove Staff Member By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Staff Member Removed",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping(value = "/remove/{staffMemberId}")
    public ResponseEntity<?> remove(@PathVariable int staffMemberId, Principal principal) {
        log.info("Entering StaffMemberController.remove");
        staffMemberFacade.removeStaffMember(staffMemberId, principal.getName());
        return ResponseEntity.ok("Staff Member Removed");
    }
}
