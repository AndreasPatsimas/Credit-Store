package gr.pwc.assignment.dto.users;

import gr.pwc.assignment.enums.BalanceActionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class EditUserBalanceDTO {

    @NotNull
    @Schema(description = "Increment/Decrement action")
    private BalanceActionType action;

    @NotNull
    @Schema(description = "ID of a User")
    private UUID userId;

    @NotNull
    @Schema(description = "Amount that would be added/subtracted with User's balance")
    private BigDecimal amount;
}
