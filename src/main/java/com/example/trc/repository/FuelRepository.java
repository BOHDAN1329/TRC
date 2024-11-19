package com.example.trc.repository;

import com.example.trc.entity.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuelRepository extends JpaRepository<Fuel, Long> {
    List<Fuel> findByTypeIgnoreCase(String type);
}
