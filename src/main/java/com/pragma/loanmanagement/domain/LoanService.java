package com.pragma.loanmanagement.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LoanService {
    Loan createLoan(String customerId, BigDecimal amount, BigDecimal interestRate,
                    LocalDate startDate, LocalDate endDate, String purpose);
    
    Loan getLoanById(UUID loanId);
    
    List<Loan> getLoansByCustomer(String customerId);
    
    Loan updateLoanStatus(UUID loanId, LoanStatus newStatus);
    
    void deleteLoan(UUID loanId);
    
    BigDecimal calculateTotalPayment(UUID loanId);
    
    List<Loan> getOverdueLoans();
    
    List<Loan> getLoansByStatus(LoanStatus status);
    
    Loan approveLoan(UUID loanId);
    
    Loan rejectLoan(UUID loanId);
}