package gr.pwc.assignment.dto.staff_members;

import gr.pwc.assignment.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class StaffMemberDTO {

    private Integer id;
    private String name;
    private String userName;
    private Role role;
}
