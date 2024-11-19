package com.example.trc.repository;

import com.example.trc.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByNameContainingIgnoreCaseOrContactNumberContaining(String name, String contactNumber);
    boolean existsByEmail(String email);
}
