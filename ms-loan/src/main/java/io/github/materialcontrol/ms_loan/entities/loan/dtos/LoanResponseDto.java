package io.github.materialcontrol.ms_loan.entities.loan.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanResponseDto {
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy hh:MM:ss")
    private LocalDateTime loanDate;

    @JsonFormat(pattern = "dd-MM-yyyy hh:MM:ss")
    private LocalDateTime loanReturn;

    private String observation;

    private Long userId;
    private Long itemId;

    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdData;
    private LocalDateTime modifiedData;
}
