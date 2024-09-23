package gr.pwc.assignment.services;

import gr.pwc.assignment.domain.UserBalance;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void init();

    UserBalance findById(UUID userId);

    List<UserBalance> findAll();
}
