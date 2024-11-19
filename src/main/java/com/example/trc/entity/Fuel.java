package com.example.trc.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Fuel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // Тип пального, наприклад, бензин, дизель

    @Column(nullable = false)
    private double pricePerLiter; // Ціна за літр

    @Column(nullable = false)
    private double availableLiters; // Наявна кількість літрів
}
