package com.pragma.loanmanagement.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoanRepository {
    Loan save(Loan loan);
    
    Optional<Loan> findById(UUID id);
    
    List<Loan> findByCustomerId(String customerId);
    
    List<Loan> findAll();
    
    void deleteById(UUID id);
    
    List<Loan> findOverdueLoans();
    
    List<Loan> findByStatus(LoanStatus status);
}