package io.github.materialcontrol.ms_loan.controllers;

import io.github.materialcontrol.ms_loan.entities.loan.Loan;
import io.github.materialcontrol.ms_loan.entities.loan.dtos.LoanFullResponseDto;
import io.github.materialcontrol.ms_loan.entities.loan.dtos.LoanMapper;
import io.github.materialcontrol.ms_loan.entities.loan.dtos.LoanRequestDto;
import io.github.materialcontrol.ms_loan.entities.loan.dtos.LoanResponseDto;
import io.github.materialcontrol.ms_loan.services.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/loan")
public class LoanControler {
    private final LoanService loanService;

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody @Valid LoanRequestDto body){
        Loan loan = loanService.create(body);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(loan.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponseDto> findById(@PathVariable("id") Long id){
        Loan loan = loanService.findById(id);

        return ResponseEntity.ok().body(LoanMapper.toDto(loan));
    }

    @GetMapping("/{id}/full")
    public ResponseEntity<LoanFullResponseDto> findFullById(@PathVariable("id") Long id){
        LoanFullResponseDto loan = loanService.findFullById(id);

        return ResponseEntity.ok().body(loan);
    }

    @PatchMapping("/{id}/return")
    public ResponseEntity<Void> finalizeLoan(@PathVariable("id") Long id){
        loanService.finalizeLoan(id);

        return ResponseEntity.noContent().build();
    }
}
