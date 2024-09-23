package gr.pwc.assignment.services.impl.calculate_user_balance;

import gr.pwc.assignment.domain.StaffMember;
import gr.pwc.assignment.enums.BalanceActionType;
import gr.pwc.assignment.exceptions.UnacceptableActionException;
import gr.pwc.assignment.services.ActionLogServiceFactory;
import gr.pwc.assignment.services.CalculateUserBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static gr.pwc.assignment.enums.ActionType.DECREMENT_BALANCE;
import static gr.pwc.assignment.enums.BalanceActionType.DECREMENT;

@Service
@RequiredArgsConstructor
public class DecrementUserBalanceService implements CalculateUserBalanceService {

    private final ActionLogServiceFactory actionLogServiceFactory;

    @Override
    public BigDecimal calculateBalance(StaffMember createdBy, String user, BigDecimal oldAmount, BigDecimal subtractAmount) {
        BigDecimal newAmount = oldAmount.subtract(subtractAmount);
        if (newAmount.compareTo(BigDecimal.ZERO) < 0)
            throw new UnacceptableActionException("Insufficient balance: The operation would result in a negative balance.");
        actionLogServiceFactory.getActionLogService(DECREMENT_BALANCE).save(createdBy, user, subtractAmount);
        return newAmount;
    }

    @Override
    public BalanceActionType getCorrelation() {
        return DECREMENT;
    }
}
