package gr.pwc.assignment.services.impl;

import gr.pwc.assignment.domain.StaffMember;
import gr.pwc.assignment.domain.UserBalance;
import gr.pwc.assignment.dto.users.EditUserBalanceDTO;
import gr.pwc.assignment.dto.users.UserBalanceDTO;
import gr.pwc.assignment.dto.users.UserBalanceDiffDTO;
import gr.pwc.assignment.dto.users.UserInfoDTO;
import gr.pwc.assignment.services.CalculateUserBalanceServiceFactory;
import gr.pwc.assignment.services.StaffMemberService;
import gr.pwc.assignment.services.UserFacade;
import gr.pwc.assignment.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    private final StaffMemberService staffMemberService;
    private final ConversionService conversionService;
    private final CalculateUserBalanceServiceFactory calculateUserBalanceServiceFactory;

    @Override
    public UserInfoDTO findUserInfoById(UUID userId) {

        log.info("Find user-info with id: {} process begins.", userId);

        UserBalance userBalance = userService.findById(userId);

        UserInfoDTO userInfo = conversionService.convert(userBalance, UserInfoDTO.class);

        log.info("Find user-info with id: {} process end.", userId);

        return userInfo;
    }

    @Override
    public List<UserBalanceDTO> findUsersBalances() {

        log.info("Find users' balances process begins.");

        List<UserBalance> users = userService.findAll();

        List<UserBalanceDTO> userBalances = users.stream()
                .map(user -> conversionService.convert(user, UserBalanceDTO.class)).toList();

        log.info("Find users' balances process end.");

        return userBalances;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserBalanceDiffDTO modifyUserBalance(EditUserBalanceDTO editUserBalance, String staffMemberUserName) {

        log.info("Modify balance of user[id: {}] by operator[username: {}] process begins.",
                editUserBalance.getUserId(), staffMemberUserName);

        StaffMember staffMember = staffMemberService.findByUserName(staffMemberUserName);
        UserBalance user = userService.findById(editUserBalance.getUserId());
        BigDecimal oldAmount = user.getBalance();
        BigDecimal newAmount = calculateUserBalanceServiceFactory
                .getCalculateUserBalanceService(editUserBalance.getAction())
                .calculateBalance(staffMember, user.getName(), user.getBalance(), editUserBalance.getAmount());
        user.setBalance(newAmount);

        log.info("Modify balance of user[id: {}] by operator[username: {}] process end.",
                editUserBalance.getUserId(), staffMemberUserName);

        return new UserBalanceDiffDTO(user.getName(), oldAmount, newAmount, editUserBalance.getAmount());
    }
}
