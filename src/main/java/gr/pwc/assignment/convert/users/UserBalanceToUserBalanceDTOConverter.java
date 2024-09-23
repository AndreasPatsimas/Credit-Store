package gr.pwc.assignment.convert.users;

import gr.pwc.assignment.domain.UserBalance;
import gr.pwc.assignment.dto.users.UserBalanceDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserBalanceToUserBalanceDTOConverter implements Converter<UserBalance, UserBalanceDTO> {

    @Override
    public UserBalanceDTO convert(UserBalance userBalance) {
        return UserBalanceDTO.builder()
                .userId(userBalance.getUserId())
                .name(userBalance.getName())
                .balance(userBalance.getBalance())
                .build();
    }
}
