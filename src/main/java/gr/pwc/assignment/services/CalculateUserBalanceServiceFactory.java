package gr.pwc.assignment.services;


import gr.pwc.assignment.enums.BalanceActionType;

public interface CalculateUserBalanceServiceFactory {

    CalculateUserBalanceService getCalculateUserBalanceService(BalanceActionType balanceActionType);
}
