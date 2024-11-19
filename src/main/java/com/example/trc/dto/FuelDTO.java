package com.example.trc.dto;

import lombok.Data;

@Data
public class FuelDTO {
    private String type; // Тип пального
    private Double pricePerLiter; // Ціна за літр
    private Double availableLiters; // Доступна кількість літрів
}
