package gr.pwc.assignment.services.impl;

import gr.pwc.assignment.domain.UserBalance;
import gr.pwc.assignment.exceptions.InitializeException;
import gr.pwc.assignment.exceptions.ResourceNotFoundException;
import gr.pwc.assignment.repositories.UserRepository;
import gr.pwc.assignment.services.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @PostConstruct
    @Transactional(propagation = Propagation.REQUIRED)
    public void init() {

        log.info("Initialize users' data process begins.");

        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        List<UserBalance> userBalances = new ArrayList<>();

        try {
            ClassPathResource resource = new ClassPathResource("user_balance_init.csv");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));

            String line;

            // Skip header
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                UUID userId = UUID.fromString(fields[0]);
                String name = fields[1];
                String vat = fields[2];
                Long counterId = parseCounterId(fields[3]);
                String address = fields[4];
                String postCode = fields[5];
                String phone = fields[6];
                LocalDate dateOfBirth = LocalDate.parse(fields[7], dateFormatter);
                BigDecimal balance = new BigDecimal(fields[8]);

                UserBalance userBalance = new UserBalance();
                userBalance.setUserId(userId);
                userBalance.setName(name);
                userBalance.setVat(vat);
                userBalance.setCounterId(counterId);
                userBalance.setAddress(address);
                userBalance.setPostCode(postCode);
                userBalance.setPhone(phone);
                userBalance.setDateOfBirth(dateOfBirth);
                userBalance.setBalance(balance);

                userBalances.add(userBalance);
            }
            userRepository.saveAll(userBalances);

        } catch (Exception e) {
            log.error("Error initializing users' data: {}", e.getMessage());
            throw new InitializeException("Initialization failed", e);
        }

        log.info("Initialize users' data process end.");
    }

    @Override
    public UserBalance findById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("User with id '%s' not found", userId)));
    }

    @Override
    public List<UserBalance> findAll() {
        return userRepository.findAll(Sort.by("name"));
    }

    private Long parseCounterId(String counterIdField) {
        try {
            return counterIdField != null && !counterIdField.isEmpty() ? Long.parseLong(counterIdField) : null;
        } catch (NumberFormatException e) {
            log.error("Parse counter id failed due to {}", e.getMessage());
            throw e;
        }
    }
}
