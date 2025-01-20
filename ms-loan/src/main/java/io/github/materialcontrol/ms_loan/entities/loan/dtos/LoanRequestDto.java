package io.github.materialcontrol.ms_loan.entities.loan.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoanRequestDto {
    @NotNull
    private Long userId;

    @NotNull
    private Long itemId;

    private String observation;
}
