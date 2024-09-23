package gr.pwc.assignment.services.impl.action_logs;

import gr.pwc.assignment.domain.ActionLog;
import gr.pwc.assignment.domain.StaffMember;
import gr.pwc.assignment.enums.ActionType;
import gr.pwc.assignment.enums.Role;
import gr.pwc.assignment.repositories.ActionLogRepository;
import gr.pwc.assignment.services.ActionLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static gr.pwc.assignment.enums.ActionType.REMOVE_STAFF_MEMBER;


@Service
@Slf4j
@RequiredArgsConstructor
public class RemoveStaffMemberActionLogService implements ActionLogService {

    private final ActionLogRepository actionLogRepository;

    @Override
    public void save(StaffMember createdBy, String staffMemberName, Object role) {
        Role roleEnum = (Role) role;
        actionLogRepository.save(ActionLog.builder()
                .actionType(REMOVE_STAFF_MEMBER)
                .createdDate(Instant.now())
                .createdBy(createdBy)
                .message(String.format(
                        "Admin [%s] removed from staff, member [%s] with role: %s.",
                        createdBy.getName(), staffMemberName, roleEnum))
                .build());
    }

    @Override
    public ActionType getCorrelation() {
        return REMOVE_STAFF_MEMBER;
    }
}
