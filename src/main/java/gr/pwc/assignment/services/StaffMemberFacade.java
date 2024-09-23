package gr.pwc.assignment.services;

import gr.pwc.assignment.dto.staff_members.StaffMemberDTO;
import gr.pwc.assignment.dto.staff_members.StaffMemberRequestDTO;

public interface StaffMemberFacade {

    StaffMemberDTO addStaffMember(StaffMemberRequestDTO staffMemberRequest, String adminUserName);

    void removeStaffMember(int staffMemberId, String adminUserName);
}
