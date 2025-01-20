package io.github.materialcontrol.ms_loan.entities.loan.dtos;

import io.github.materialcontrol.ms_loan.entities.loan.Loan;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.time.LocalDateTime;

public class LoanMapper {
    public static LoanResponseDto toDto(Loan loan){
        return new ModelMapper().map(loan, LoanResponseDto.class);
    }
}
