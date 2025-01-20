package io.github.materialcontrol.ms_loan.services;

import feign.FeignException;
import io.github.materialcontrol.ms_loan.entities.loan.Loan;
import io.github.materialcontrol.ms_loan.entities.loan.dtos.LoanFullResponseDto;
import io.github.materialcontrol.ms_loan.entities.loan.dtos.LoanRequestDto;
import io.github.materialcontrol.ms_loan.exception.FeingRequestException;
import io.github.materialcontrol.ms_loan.exception.ItemUnavailableException;
import io.github.materialcontrol.ms_loan.exception.NotFoundException;
import io.github.materialcontrol.ms_loan.repositories.LoanRepository;
import io.github.materialcontrol.ms_loan.services.client.dtos.ItemResponseDto;
import io.github.materialcontrol.ms_loan.services.client.dtos.UserResponseDto;
import io.github.materialcontrol.ms_loan.services.client.ItemClient;
import io.github.materialcontrol.ms_loan.services.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final ItemClient itemClient;
    private final UserClient userClient;

    @Transactional
    public Loan create(LoanRequestDto dto) {
        try {
            ItemResponseDto item = itemClient.findById(dto.getItemId());
            if (item.getStatus().equals("UNAVAILABLE"))
                throw new ItemUnavailableException("Requested item unavailable");

            UserResponseDto user = userClient.findById(dto.getUserId());
            Loan loan = new Loan(LocalDateTime.now(), dto.getObservation(), user.getId(), item.getId());
            itemClient.updateStatus(loan.getItemId());
            loanRepository.save(loan);

            return loan;
        } catch (FeignException.NotFound e) {
            throw new NotFoundException("Feign: Item or User not found");
        } catch (FeignException.ServiceUnavailable e){
            throw new FeingRequestException("Feign: Item or User service unavailable");
        }

    }

    @Transactional(readOnly = true)
    public Loan findById(Long id) {
        return loanRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Loan with id = %s not found", id))
        );
    }

    @Transactional(readOnly = true)
    public LoanFullResponseDto findFullById(Long id) {
        Loan loan = findById(id);
        try {
            ItemResponseDto item = itemClient.findById(loan.getItemId());
            UserResponseDto user = userClient.findById(loan.getUserId());

            return new LoanFullResponseDto(loan, user, item);
        } catch (FeignException.ServiceUnavailable e){
            throw new FeingRequestException("Feign: Item or User service unavailable");
        }
    }

    @Transactional
    public void finalizeLoan(Long id) {
        Loan loan = findById(id);
        loan.setReturnDate(LocalDateTime.now());
        itemClient.updateStatus(loan.getItemId());
    }
}
