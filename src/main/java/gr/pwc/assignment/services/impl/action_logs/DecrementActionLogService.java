package gr.pwc.assignment.services.impl.action_logs;

import gr.pwc.assignment.domain.ActionLog;
import gr.pwc.assignment.domain.StaffMember;
import gr.pwc.assignment.enums.ActionType;
import gr.pwc.assignment.repositories.ActionLogRepository;
import gr.pwc.assignment.services.ActionLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

import static gr.pwc.assignment.enums.ActionType.DECREMENT_BALANCE;

@Service
@Slf4j
@RequiredArgsConstructor
public class DecrementActionLogService implements ActionLogService {

    private final ActionLogRepository actionLogRepository;

    @Override
    public void save(StaffMember createdBy, String user, Object amount) {
        BigDecimal changedAmount = (BigDecimal) amount;
        actionLogRepository.save(ActionLog.builder()
                .actionType(DECREMENT_BALANCE)
                .createdDate(Instant.now())
                .createdBy(createdBy)
                .message(String.format(
                        "Operator [%s] decremented from user [%s] the amount: %s.",
                        createdBy.getName(), user, changedAmount))
                .build());
    }

    @Override
    public ActionType getCorrelation() {
        return DECREMENT_BALANCE;
    }
}
