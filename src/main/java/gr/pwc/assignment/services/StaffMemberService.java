package gr.pwc.assignment.services;

import gr.pwc.assignment.domain.StaffMember;

public interface StaffMemberService {

    void init();

    StaffMember findByUserName(String userName);

    StaffMember findById(int staffMemberId);

    StaffMember save(StaffMember staffMember);

    void delete(StaffMember staffMember);
}
