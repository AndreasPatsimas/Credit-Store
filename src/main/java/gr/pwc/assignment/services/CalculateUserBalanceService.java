package gr.pwc.assignment.services;

import gr.pwc.assignment.domain.StaffMember;
import gr.pwc.assignment.enums.BalanceActionType;

import java.math.BigDecimal;

public interface CalculateUserBalanceService {

    BigDecimal calculateBalance(StaffMember createdBy, String user, BigDecimal oldAmount, BigDecimal editAmount);

    BalanceActionType getCorrelation();
}
