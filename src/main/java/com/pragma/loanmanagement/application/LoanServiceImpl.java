package com.pragma.loanmanagement.application;

import com.pragma.loanmanagement.domain.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class LoanServiceImpl implements LoanService {
    
    private final LoanRepository loanRepository;
    private final NotificationService notificationService;
    private final List<String> auditLog;
    private BigDecimal totalInterestCollected;
    private int approvalCount;
    private int rejectionCount;
    
    public LoanServiceImpl(LoanRepository loanRepository, NotificationService notificationService) {
        if (loanRepository == null) {
            throw new IllegalArgumentException("LoanRepository cannot be null");
        }
        if (notificationService == null) {
            throw new IllegalArgumentException("NotificationService cannot be null");
        }
        this.loanRepository = loanRepository;
        this.notificationService = notificationService;
        this.auditLog = new ArrayList<>();
        this.totalInterestCollected = BigDecimal.ZERO;
        this.approvalCount = 0;
        this.rejectionCount = 0;
    }
    
    @Override
    public Loan createLoan(String customerId, BigDecimal amount, BigDecimal interestRate,
                          LocalDate startDate, LocalDate endDate, String purpose) {
        validateLoanInput(customerId, amount, interestRate, startDate, endDate, purpose);
        
        UUID loanId = UUID.randomUUID();
        Loan loan = new Loan(loanId, customerId, amount, interestRate, startDate, endDate, purpose);
        
        Loan savedLoan = loanRepository.save(loan);
        
        logAction("Loan created: " + loanId + " for customer: " + customerId + 
                 " | Amount: " + amount + " | Rate: " + interestRate + "%");
        
        return savedLoan;
    }
    
    @Override
    public Loan getLoanById(UUID loanId) {
        if (loanId == null) {
            throw new IllegalArgumentException("Loan ID cannot be null");
        }
        
        Optional<Loan> loan = loanRepository.findById(loanId);
        if (loan.isEmpty()) {
            logAction("Loan not found: " + loanId);
            throw new RuntimeException("Loan not found with ID: " + loanId);
        }
        
        logAction("Loan retrieved: " + loanId);
        return loan.get();
    }
    
    @Override
    public List<Loan> getLoansByCustomer(String customerId) {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be null or empty");
        }
        
        List<Loan> loans = loanRepository.findByCustomerId(customerId);
        logAction("Retrieved " + loans.size() + " loans for customer: " + customerId);
        
        return loans;
    }
    
    @Override
    public Loan updateLoanStatus(UUID loanId, LoanStatus newStatus) {
        Loan loan = getLoanById(loanId);
        Loan updatedLoan = loan.withStatus(newStatus);
        Loan savedLoan = loanRepository.save(updatedLoan);
        
        logAction("Loan status updated: " + loanId + " | Old status: " + loan.getStatus() + 
                 " | New status: " + newStatus);
        
        return savedLoan;
    }
    
    @Override
    public void deleteLoan(UUID loanId) {
        if (loanId == null) {
            throw new IllegalArgumentException("Loan ID cannot be null");
        }
        
        Loan loan = getLoanById(loanId);
        
        if (loan.getStatus() == LoanStatus.ACTIVE || loan.getStatus() == LoanStatus.OVERDUE) {
            throw new IllegalStateException("Cannot delete active or overdue loans");
        }
        
        loanRepository.deleteById(loanId);
        logAction("Loan deleted: " + loanId);
    }
    
    @Override
    public BigDecimal calculateTotalPayment(UUID loanId) {
        Loan loan = getLoanById(loanId);
        return loan.calculateTotalPayment();
    }
    
    @Override
    public List<Loan> getOverdueLoans() {
        List<Loan> overdueLoans = loanRepository.findOverdueLoans();
        logAction("Retrieved " + overdueLoans.size() + " overdue loans");
        return overdueLoans;
    }
    
    @Override
    public List<Loan> getLoansByStatus(LoanStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        
        List<Loan> loans = loanRepository.findByStatus(status);
        logAction("Retrieved " + loans.size() + " loans with status: " + status);
        return loans;
    }
    
    @Override
    public Loan approveLoan(UUID loanId) {
        Loan loan = getLoanById(loanId);
        
        if (loan.getStatus() != LoanStatus.PENDING) {
            throw new IllegalStateException("Only pending loans can be approved. Current status: " + loan.getStatus());
        }
        
        BigDecimal totalPayment = loan.calculateTotalPayment();
        BigDecimal interest = totalPayment.subtract(loan.getAmount());
        this.totalInterestCollected = this.totalInterestCollected.add(interest);
        
        Loan approvedLoan = loan.withStatus(LoanStatus.ACTIVE);
        Loan savedLoan = loanRepository.save(approvedLoan);
        
        notificationService.sendLoanApprovedNotification(loan.getCustomerId(), savedLoan);
        
        approvalCount++;
        logAction("Loan approved: " + loanId + " | Interest earned: " + interest);
        
        return savedLoan;
    }
    
    @Override
    public Loan rejectLoan(UUID loanId) {
        Loan loan = getLoanById(loanId);
        
        if (loan.getStatus() != LoanStatus.PENDING) {
            throw new IllegalStateException("Only pending loans can be rejected. Current status: " + loan.getStatus());
        }
        
        String rejectionReason = determineRejectionReason(loan);
        
        Loan rejectedLoan = loan.withStatus(LoanStatus.REJECTED);
        Loan savedLoan = loanRepository.save(rejectedLoan);
        
        notificationService.sendLoanRejectedNotification(loan.getCustomerId(), savedLoan, rejectionReason);
        
        rejectionCount++;
        logAction("Loan rejected: " + loanId + " | Reason: " + rejectionReason);
        
        return savedLoan;
    }
    
    private void validateLoanInput(String customerId, BigDecimal amount, BigDecimal interestRate,
                                   LocalDate startDate, LocalDate endDate, String purpose) {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be null or empty");
        }
        
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Loan amount must be greater than zero");
        }
        
        if (amount.compareTo(new BigDecimal("1000000")) > 0) {
            throw new IllegalArgumentException("Loan amount exceeds maximum allowed (1,000,000)");
        }
        
        if (interestRate == null || interestRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative");
        }
        
        if (interestRate.compareTo(new BigDecimal("50")) > 0) {
            throw new IllegalArgumentException("Interest rate exceeds maximum allowed (50%)");
        }
        
        if (startDate == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        }
        
        if (endDate == null) {
            throw new IllegalArgumentException("End date cannot be null");
        }
        
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        
        long monthsBetween = ChronoUnit.MONTHS.between(startDate, endDate);
        if (monthsBetween > 360) {
            throw new IllegalArgumentException("Loan term cannot exceed 30 years (360 months)");
        }
        
        if (purpose == null || purpose.trim().isEmpty()) {
            throw new IllegalArgumentException("Loan purpose cannot be null or empty");
        }
    }
    
    private String determineRejectionReason(Loan loan) {
        if (loan.getAmount().compareTo(new BigDecimal("500000")) > 0) {
            return "Loan amount exceeds credit limit";
        }
        
        if (loan.getInterestRate().compareTo(new BigDecimal("30")) > 0) {
            return "Interest rate too high for approval";
        }
        
        long months = loan.getLoanTermInMonths();
        if (months > 240) {
            return "Loan term too long";
        }
        
        return "Does not meet credit criteria";
    }
    
    private void logAction(String action) {
        String logEntry = java.time.LocalDateTime.now() + ": " + action;
        auditLog.add(logEntry);
        System.out.println("[AUDIT] " + logEntry);
    }
    
    public List<String> getAuditLog() {
        return new ArrayList<>(auditLog);
    }
    
    public BigDecimal getTotalInterestCollected() {
        return totalInterestCollected;
    }
    
    public int getApprovalCount() {
        return approvalCount;
    }
    
    public int getRejectionCount() {
        return rejectionCount;
    }
}