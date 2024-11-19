package com.example.trc.service;

import com.example.trc.entity.Fuel;
import com.example.trc.entity.Transaction;
import com.example.trc.repository.FuelRepository;
import com.example.trc.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final FuelService fuelService;
    private final FuelRepository fuelRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, FuelService fuelService, FuelRepository fuelRepository) {
        this.transactionRepository = transactionRepository;
        this.fuelService = fuelService;
        this.fuelRepository = fuelRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByClient(Long clientId) {
        return transactionRepository.findByClientId(clientId);
    }

    public double getTotalSalesForPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return transactionRepository.findByTransactionDateBetween(startDate, endDate)
                .stream()
                .mapToDouble(Transaction::getTotalPrice)
                .sum();
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public Transaction saveTransaction(Transaction transaction) {
        Fuel fuel = fuelRepository.findById(transaction.getFuel().getId()).get();
        transaction.setFuel(fuel);

        if (fuel.getAvailableLiters() < transaction.getQuantity()) {
            throw new RuntimeException("Insufficient fuel available");
        }

        fuel.setAvailableLiters(fuel.getAvailableLiters() - transaction.getQuantity());
        fuelService.saveFuel(fuel);

        transaction.setTotalPrice(transaction.getQuantity() * fuel.getPricePerLiter());
        transaction.setTransactionDate(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
