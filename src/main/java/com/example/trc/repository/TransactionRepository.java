package com.example.trc.repository;

import com.example.trc.entity.Fuel;
import com.example.trc.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByClientId(Long clientId);
    List<Transaction> findByTransactionDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Transaction> findTransactionByFuel(Fuel fuel);
}
