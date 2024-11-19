package com.example.trc.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction; // Прив'язка до транзакції

    @Column(nullable = false)
    private String receiptDetails; // Текст квитанції (можна зберігати як JSON)
}
