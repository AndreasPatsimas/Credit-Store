package gr.pwc.assignment.services.impl;

import gr.pwc.assignment.domain.Authority;
import gr.pwc.assignment.enums.Role;
import gr.pwc.assignment.repositories.AuthorityRepository;
import gr.pwc.assignment.services.AuthorityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void init() {

        log.info("Initialize roles' data process begins.");

        authorityRepository.saveAll(List.of(
                new Authority((short) 1, Role.ROLE_ADMIN),
                new Authority((short) 2, Role.ROLE_OPERATOR)
        ));

        log.info("Initialize roles' data process end.");
    }
}
