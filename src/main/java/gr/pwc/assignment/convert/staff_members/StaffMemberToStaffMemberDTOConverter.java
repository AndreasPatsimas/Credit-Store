package gr.pwc.assignment.convert.staff_members;

import gr.pwc.assignment.domain.StaffMember;
import gr.pwc.assignment.dto.staff_members.StaffMemberDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StaffMemberToStaffMemberDTOConverter implements Converter<StaffMember, StaffMemberDTO> {

    @Override
    public StaffMemberDTO convert(StaffMember staffMember) {
        return StaffMemberDTO.builder()
                .id(staffMember.getId())
                .name(staffMember.getName())
                .userName(staffMember.getUserName())
                .role(staffMember.getAuthority().getDescription())
                .build();
    }
}
