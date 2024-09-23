package gr.pwc.assignment.services;

import gr.pwc.assignment.dto.users.EditUserBalanceDTO;
import gr.pwc.assignment.dto.users.UserBalanceDTO;
import gr.pwc.assignment.dto.users.UserBalanceDiffDTO;
import gr.pwc.assignment.dto.users.UserInfoDTO;

import java.util.List;
import java.util.UUID;

public interface UserFacade {

    UserInfoDTO findUserInfoById(UUID userId);

    List<UserBalanceDTO> findUsersBalances();

    UserBalanceDiffDTO modifyUserBalance(EditUserBalanceDTO editUserBalance, String staffMemberUserName);
}
