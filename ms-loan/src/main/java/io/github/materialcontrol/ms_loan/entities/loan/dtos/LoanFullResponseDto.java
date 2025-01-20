package io.github.materialcontrol.ms_loan.entities.loan.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.materialcontrol.ms_loan.entities.loan.Loan;
import io.github.materialcontrol.ms_loan.services.client.dtos.ItemResponseDto;
import io.github.materialcontrol.ms_loan.services.client.dtos.UserResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class LoanFullResponseDto {
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy hh:MM:ss")
    private LocalDateTime loanDate;
    @JsonFormat(pattern = "dd-MM-yyyy hh:MM:ss")
    private LocalDateTime loanReturn;

    private String observation;
    private UserResponseDto user;
    private ItemResponseDto item;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdData;
    private LocalDateTime modifiedData;

    public LoanFullResponseDto(Loan loan, UserResponseDto user, ItemResponseDto item){
        this.id = loan.getId();
        this.loanDate = loan.getLoanDate();
        this.loanReturn = loan.getReturnDate();
        this.observation = loan.getObservation();
        this.user = user;
        this.item = item;
    }
}
