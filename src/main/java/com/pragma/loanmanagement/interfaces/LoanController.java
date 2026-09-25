package com.pragma.loanmanagement.interfaces;

import com.pragma.loanmanagement.domain.Loan;
import com.pragma.loanmanagement.domain.LoanService;
import com.pragma.loanmanagement.domain.LoanStatus;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {
    private final LoanService loanService;
    private final Map<String, Object> metricsCache = new HashMap<>();
    private long requestCount = 0;
    
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody Map<String, Object> request) {
        requestCount++;
        
        String customerId = (String) request.get("customerId");
        BigDecimal amount = new BigDecimal(request.get("amount").toString());
        BigDecimal interestRate = new BigDecimal(request.get("interestRate").toString());
        LocalDate startDate = LocalDate.parse((String) request.get("startDate"));
        LocalDate endDate = LocalDate.parse((String) request.get("endDate"));
        String purpose = (String) request.get("purpose");
        
        Loan loan = loanService.createLoan(
            customerId, amount, interestRate, startDate, endDate, purpose
        );
        
        return ResponseEntity.status(HttpStatus.CREATED).body(loan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable UUID id) {
        requestCount++;
        
        Loan loan = loanService.getLoanById(id);
        if (loan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(loan);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Loan>> getLoansByCustomer(@PathVariable String customerId) {
        requestCount++;
        
        List<Loan> loans = loanService.getLoansByCustomer(customerId);
        return ResponseEntity.ok(loans);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Loan> updateLoanStatus(
            @PathVariable UUID id, 
            @RequestBody Map<String, String> request) {
        requestCount++;
        
        String statusStr = request.get("status");
        LoanStatus newStatus = LoanStatus.valueOf(statusStr);
        
        Loan updatedLoan = loanService.updateLoanStatus(id, newStatus);
        if (updatedLoan == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(updatedLoan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable UUID id) {
        requestCount++;
        
        loanService.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/total-payment")
    public ResponseEntity<Map<String, BigDecimal>> calculateTotalPayment(@PathVariable UUID id) {
        requestCount++;
        
        BigDecimal totalPayment = loanService.calculateTotalPayment(id);
        Map<String, BigDecimal> response = new HashMap<>();
        response.put("totalPayment", totalPayment);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<Loan>> getOverdueLoans() {
        requestCount++;
        
        List<Loan> overdueLoans = loanService.getOverdueLoans();
        return ResponseEntity.ok(overdueLoans);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Loan>> getLoansByStatus(@PathVariable String status) {
        requestCount++;
        
        LoanStatus loanStatus = LoanStatus.valueOf(status);
        List<Loan> loans = loanService.getLoansByStatus(loanStatus);
        return ResponseEntity.ok(loans);
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<Loan> approveLoan(@PathVariable UUID id) {
        requestCount++;
        
        Loan approvedLoan = loanService.approveLoan(id);
        if (approvedLoan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(approvedLoan);
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<Loan> rejectLoan(@PathVariable UUID id) {
        requestCount++;
        
        Loan rejectedLoan = loanService.rejectLoan(id);
        if (rejectedLoan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rejectedLoan);
    }

    @GetMapping("/metrics")
    public ResponseEntity<Map<String, Object>> getMetrics() {
        metricsCache.put("totalRequests", requestCount);
        metricsCache.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(new HashMap<>(metricsCache));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Loan>> searchLoans(
            @RequestParam(required = false) String customerId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) BigDecimal minAmount,
            @RequestParam(required = false) BigDecimal maxAmount) {
        
        requestCount++;
        List<Loan> allLoans = loanService.getLoansByCustomer(
            customerId != null ? customerId : ""
        );
        
        if (customerId == null) {
            allLoans = loanService.findAll();
        }
        
        List<Loan> filtered = allLoans.stream()
            .filter(loan -> status == null || loan.getStatus().name().equals(status))
            .filter(loan -> minAmount == null || loan.getAmount().compareTo(minAmount) >= 0)
            .filter(loan -> maxAmount == null || loan.getAmount().compareTo(maxAmount) <= 0)
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(filtered);
    }
}