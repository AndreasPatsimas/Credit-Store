package gr.pwc.assignment.dto.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class UserBalanceDTO {

    private UUID userId;
    private String name;
    private BigDecimal balance;
}
