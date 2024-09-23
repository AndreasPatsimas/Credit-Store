package gr.pwc.assignment.services.impl.calculate_user_balance;

import gr.pwc.assignment.enums.BalanceActionType;
import gr.pwc.assignment.services.CalculateUserBalanceService;
import gr.pwc.assignment.services.CalculateUserBalanceServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;

@Service
public class CalculateUserBalanceServiceFactoryImpl implements CalculateUserBalanceServiceFactory {

    private final EnumMap<BalanceActionType, CalculateUserBalanceService> calculateUserBalanceServiceMap;

    @Autowired
    public CalculateUserBalanceServiceFactoryImpl(List<CalculateUserBalanceService> calculateUserBalanceServices){
        calculateUserBalanceServiceMap = new EnumMap<>(BalanceActionType.class);
        calculateUserBalanceServices.forEach(calculateUserBalanceService ->
                calculateUserBalanceServiceMap.put(calculateUserBalanceService.getCorrelation(), calculateUserBalanceService)
        );
    }

    @Override
    public CalculateUserBalanceService getCalculateUserBalanceService(BalanceActionType balanceActionType) {
        return calculateUserBalanceServiceMap.get(balanceActionType);
    }
}
