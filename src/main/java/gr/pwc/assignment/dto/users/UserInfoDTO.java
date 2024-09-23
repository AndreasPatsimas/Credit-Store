package gr.pwc.assignment.dto.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Data
@Builder
public class UserInfoDTO {

    private String name;
    private String vat;
    private String address;
    private String postCode;
    private String phone;
    private LocalDate dateOfBirth;
}
