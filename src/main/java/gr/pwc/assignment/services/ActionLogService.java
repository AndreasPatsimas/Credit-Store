package gr.pwc.assignment.services;

import gr.pwc.assignment.domain.StaffMember;
import gr.pwc.assignment.enums.ActionType;

public interface ActionLogService {

    void save(StaffMember createdBy, String name, Object value);

    ActionType getCorrelation();
}
