package gr.pwc.assignment.dto.staff_members;

import gr.pwc.assignment.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StaffMemberRequestDTO {

    @Schema(description = "Name of new Staff-Member")
    private String name;

    @NotNull
    @Schema(description = "Username of new Staff-Member")
    private String userName;

    @NotNull
    @Schema(description = "Password of new Staff-Member")
    private String password;

    @NotNull
    @Schema(description = "Role of new Staff-Member")
    private Role role;
}
