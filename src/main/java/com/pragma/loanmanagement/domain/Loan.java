package com.pragma.loanmanagement.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Loan {
    private final UUID id;
    private final String customerId;
    private final BigDecimal amount;
    private final BigDecimal interestRate;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final LoanStatus status;
    private final String purpose;

    public Loan(UUID id, String customerId, BigDecimal amount, BigDecimal interestRate,
                LocalDate startDate, LocalDate endDate, LoanStatus status, String purpose) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.purpose = purpose;
    }

    public UUID getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public String getPurpose() {
        return purpose;
    }

    public BigDecimal calculateTotalPayment() {
        BigDecimal totalInterest = amount.multiply(interestRate).multiply(BigDecimal.valueOf(getLoanTermInMonths()));
        return amount.add(totalInterest);
    }

    public int getLoanTermInMonths() {
        return (int) java.time.temporal.ChronoUnit.MONTHS.between(startDate, endDate);
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(endDate) && status != LoanStatus.PAID;
    }

    public Loan withStatus(LoanStatus newStatus) {
        return new Loan(this.id, this.customerId, this.amount, this.interestRate,
                       this.startDate, this.endDate, newStatus, this.purpose);
    }
}

enum LoanStatus {
    ACTIVE,
    PAID,
    OVERDUE,
    DEFAULTED
}