package gr.pwc.assignment.services.impl;

import gr.pwc.assignment.domain.StaffMember;
import gr.pwc.assignment.dto.staff_members.StaffMemberDTO;
import gr.pwc.assignment.dto.staff_members.StaffMemberRequestDTO;
import gr.pwc.assignment.services.ActionLogServiceFactory;
import gr.pwc.assignment.services.StaffMemberFacade;
import gr.pwc.assignment.services.StaffMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static gr.pwc.assignment.enums.ActionType.ADD_STAFF_MEMBER;
import static gr.pwc.assignment.enums.ActionType.REMOVE_STAFF_MEMBER;

@Service
@Slf4j
@RequiredArgsConstructor
public class StaffMemberFacadeImpl implements StaffMemberFacade {

    private final StaffMemberService staffMemberService;
    private final ConversionService conversionService;
    private final ActionLogServiceFactory actionLogServiceFactory;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public StaffMemberDTO addStaffMember(StaffMemberRequestDTO staffMemberRequest, String adminUserName) {

        log.info("Add StaffMember[{}] by admin: {} process begins", staffMemberRequest, adminUserName);

        StaffMember admin = staffMemberService.findByUserName(adminUserName);
        StaffMember staffMember = conversionService.convert(staffMemberRequest, StaffMember.class);
        staffMember = staffMemberService.save(staffMember);
        StaffMemberDTO staffMemberDTO = conversionService.convert(staffMember, StaffMemberDTO.class);
        actionLogServiceFactory.getActionLogService(ADD_STAFF_MEMBER)
                .save(admin, staffMember.getName(), staffMember.getAuthority().getDescription());

        log.info("Add StaffMember[{}] by admin: {} process end", staffMemberRequest, adminUserName);

        return staffMemberDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeStaffMember(int staffMemberId, String adminUserName) {

        log.info("Remove StaffMember[id: {}] by admin: {} process begins", staffMemberId, adminUserName);

        StaffMember staffMember = staffMemberService.findById(staffMemberId);
        StaffMember admin = staffMemberService.findByUserName(adminUserName);
        staffMemberService.delete(staffMember);
        actionLogServiceFactory.getActionLogService(REMOVE_STAFF_MEMBER)
                .save(admin, staffMember.getName(), staffMember.getAuthority().getDescription());

        log.info("Remove StaffMember[id: {}] by admin: {} process end", staffMemberId, adminUserName);
    }
}
