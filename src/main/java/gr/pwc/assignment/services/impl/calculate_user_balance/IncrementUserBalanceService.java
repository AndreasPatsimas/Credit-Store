package gr.pwc.assignment.services.impl.calculate_user_balance;

import gr.pwc.assignment.domain.StaffMember;
import gr.pwc.assignment.enums.BalanceActionType;
import gr.pwc.assignment.services.ActionLogServiceFactory;
import gr.pwc.assignment.services.CalculateUserBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static gr.pwc.assignment.enums.ActionType.INCREMENT_BALANCE;
import static gr.pwc.assignment.enums.BalanceActionType.INCREMENT;

@Service
@RequiredArgsConstructor
public class IncrementUserBalanceService implements CalculateUserBalanceService {

    private final ActionLogServiceFactory actionLogServiceFactory;

    @Override
    public BigDecimal calculateBalance(StaffMember createdBy, String user, BigDecimal oldAmount, BigDecimal addAmount) {
        actionLogServiceFactory.getActionLogService(INCREMENT_BALANCE).save(createdBy, user, addAmount);
        return oldAmount.add(addAmount);
    }

    @Override
    public BalanceActionType getCorrelation() {
        return INCREMENT;
    }
}
