package gr.pwc.assignment.services.impl;

import gr.pwc.assignment.domain.Authority;
import gr.pwc.assignment.domain.StaffMember;
import gr.pwc.assignment.enums.Role;
import gr.pwc.assignment.exceptions.InitializeException;
import gr.pwc.assignment.exceptions.ResourceNotFoundException;
import gr.pwc.assignment.repositories.StaffMemberRepository;
import gr.pwc.assignment.services.AuthorityService;
import gr.pwc.assignment.services.StaffMemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StaffMemberServiceImpl implements StaffMemberService {

    private final StaffMemberRepository staffMemberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityService authorityService;

    @Override
    @PostConstruct
    @Transactional(propagation = Propagation.REQUIRED)
    public void init() {

        log.info("Initialize staff-members' data process begins.");

        authorityService.init();

        List<StaffMember> staffMembers = new ArrayList<>();

        try {
            ClassPathResource resource = new ClassPathResource("operators_admins_init.csv");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));

            String line;

            // Skip header
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                String name = fields[0];
                String userName = fields[1];
                String password = fields[2];
                String role = fields[3];
                Authority authority = buildAuthority(role);

                StaffMember staffMember = new StaffMember();
                staffMember.setName(name);
                staffMember.setUserName(userName);
                staffMember.setPassword(passwordEncoder.encode(password));
                staffMember.setAuthority(authority);

                staffMembers.add(staffMember);
            }
            staffMemberRepository.saveAll(staffMembers);

        } catch (Exception e) {
            log.error("Error initializing staff-members' data: {}", e.getMessage());
            throw new InitializeException("Initialization failed", e);
        }

        log.info("Initialize staff-members' data process end.");
    }

    @Override
    public StaffMember findByUserName(String userName) {
        return staffMemberRepository.findByUserName(userName).orElseThrow(
                () -> new ResourceNotFoundException(String.format("StaffMember with userName '%s' not found", userName)));
    }

    @Override
    public StaffMember findById(int staffMemberId) {
        return staffMemberRepository.findById(staffMemberId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("StaffMember with id: '%d' not found", staffMemberId)));
    }

    @Override
    public StaffMember save(StaffMember staffMember) {
        return staffMemberRepository.save(staffMember);
    }

    @Override
    public void delete(StaffMember staffMember) {
        staffMemberRepository.delete(staffMember);
    }

    private Authority buildAuthority(String role) {
        if ("OPERATOR".equals(role))
            return new Authority((short) 2, Role.ROLE_OPERATOR);
        return new Authority((short) 1, Role.ROLE_ADMIN);
    }
}
