package gr.pwc.assignment.convert.staff_members;

import gr.pwc.assignment.domain.Authority;
import gr.pwc.assignment.domain.StaffMember;
import gr.pwc.assignment.dto.staff_members.StaffMemberRequestDTO;
import gr.pwc.assignment.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StaffMemberRequestDTOToStaffMemberConverter implements Converter<StaffMemberRequestDTO, StaffMember> {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public StaffMember convert(StaffMemberRequestDTO staffMemberRequest) {
        return StaffMember.builder()
                .name(staffMemberRequest.getName())
                .userName(staffMemberRequest.getUserName())
                .password(bCryptPasswordEncoder.encode(staffMemberRequest.getPassword()))
                .authority(buildAuthority(staffMemberRequest.getRole()))
                .build();
    }

    private Authority buildAuthority(Role role) {

        if (Role.ROLE_ADMIN.equals(role))
            return new Authority((short) 1, Role.ROLE_ADMIN);

        return new Authority((short) 2, Role.ROLE_OPERATOR);
    }
}
