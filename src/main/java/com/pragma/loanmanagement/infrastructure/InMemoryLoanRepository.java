package com.pragma.loanmanagement.infrastructure;

import com.pragma.loanmanagement.domain.Loan;
import com.pragma.loanmanagement.domain.LoanRepository;
import com.pragma.loanmanagement.domain.LoanStatus;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class InMemoryLoanRepository implements LoanRepository {
    private final Map<UUID, Loan> loansStore = new ConcurrentHashMap<>();
    private final AtomicReference<UUID> idGenerator = new AtomicReference<>(UUID.randomUUID());

    @Override
    public Loan save(Loan loan) {
        if (loan == null) {
            throw new IllegalArgumentException("El préstamo no puede ser nulo");
        }
        
        Loan loanToSave;
        if (loan.getId() == null) {
            UUID newId = UUID.randomUUID();
            loanToSave = new Loan(
                newId,
                loan.getCustomerId(),
                loan.getAmount(),
                loan.getInterestRate(),
                loan.getStartDate(),
                loan.getEndDate(),
                loan.getStatus(),
                loan.getPurpose()
            );
        } else {
            loanToSave = loan;
        }
        
        loansStore.put(loanToSave.getId(), loanToSave);
        return loanToSave;
    }

    @Override
    public Optional<Loan> findById(UUID id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(loansStore.get(id));
    }

    @Override
    public List<Loan> findByCustomerId(String customerId) {
        if (customerId == null || customerId.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return loansStore.values().stream()
            .filter(loan -> customerId.equals(loan.getCustomerId()))
            .collect(Collectors.toList());
    }

    @Override
    public List<Loan> findAll() {
        return new ArrayList<>(loansStore.values());
    }

    @Override
    public void deleteById(UUID id) {
        if (id != null) {
            loansStore.remove(id);
        }
    }

    @Override
    public List<Loan> findOverdueLoans() {
        LocalDate today = LocalDate.now();
        return loansStore.values().stream()
            .filter(loan -> loan.getStatus() == LoanStatus.ACTIVE)
            .filter(loan -> loan.getEndDate().isBefore(today))
            .collect(Collectors.toList());
    }

    @Override
    public List<Loan> findByStatus(LoanStatus status) {
        if (status == null) {
            return Collections.emptyList();
        }
        return loansStore.values().stream()
            .filter(loan -> status.equals(loan.getStatus()))
            .collect(Collectors.toList());
    }
}