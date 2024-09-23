package gr.pwc.assignment.dto.users;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class UserBalanceDiffDTO {

    private String user;
    private BigDecimal oldBalance;
    private BigDecimal newBalance;
    private BigDecimal diff;
}
