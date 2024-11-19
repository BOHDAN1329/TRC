package com.example.trc.repository;

import com.example.trc.entity.Receipt;
import com.example.trc.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    @Modifying
    @Query("DELETE FROM Receipt r WHERE r.transaction IN :transactions")
    void deleteByTransaction(@Param("transactions") List<Transaction> transactions);
}
