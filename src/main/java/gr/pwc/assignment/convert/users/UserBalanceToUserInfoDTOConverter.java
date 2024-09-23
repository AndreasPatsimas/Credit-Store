package gr.pwc.assignment.convert.users;

import gr.pwc.assignment.domain.UserBalance;
import gr.pwc.assignment.dto.users.UserInfoDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserBalanceToUserInfoDTOConverter implements Converter<UserBalance, UserInfoDTO> {

    @Override
    public UserInfoDTO convert(UserBalance userBalance) {
        return UserInfoDTO.builder()
                .name(userBalance.getName())
                .vat(userBalance.getVat())
                .address(userBalance.getAddress())
                .postCode(userBalance.getPostCode())
                .phone(userBalance.getPhone())
                .dateOfBirth(userBalance.getDateOfBirth())
                .build();
    }
}
