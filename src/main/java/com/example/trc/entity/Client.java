package com.example.trc.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Ім'я клієнта

    @Column(nullable = false, unique = true)
    private String contactNumber; // Контактний номер

    @Column(nullable = false, unique = true)
    private String email; // Email клієнта
}
