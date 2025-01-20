package io.github.materialcontrol.ms_loan.repositories;

import io.github.materialcontrol.ms_loan.entities.loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}