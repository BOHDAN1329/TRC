package com.example.trc.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fuel_id", nullable = false)
    private Fuel fuel; // Яке пальне продано

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client; // Кому продано

    @Column(nullable = false)
    private double quantity; // Кількість літрів

    @Column(nullable = false)
    private double totalPrice; // Загальна ціна

    @Column(nullable = false)
    private LocalDateTime transactionDate; // Дата продажу
}
