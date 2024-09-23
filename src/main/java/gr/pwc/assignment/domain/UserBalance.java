package gr.pwc.assignment.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserBalance implements Serializable {

    @Id
    private UUID userId;

    private String name;

    private String vat;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long counterId;

    private String address;

    private String postCode;

    private String phone;

    private LocalDate dateOfBirth;

    @Min(0)
    @Column(precision = 15, scale = 2)
    private BigDecimal balance;
}
