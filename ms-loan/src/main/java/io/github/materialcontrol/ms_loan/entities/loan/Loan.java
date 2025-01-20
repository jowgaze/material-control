package io.github.materialcontrol.ms_loan.entities.loan;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_loan")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Loan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private LocalDateTime loanDate;

    private LocalDateTime returnDate;

    @NonNull
    @Column(length = 300)
    private String observation;

    @NonNull
    @Column(nullable = false)
    private Long userId;

    @NonNull
    @Column(nullable = false)
    private Long itemId;

    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdData;
    private LocalDateTime modifiedData;
}
