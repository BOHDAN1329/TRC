package com.example.trc.service;

import com.example.trc.entity.Fuel;
import com.example.trc.entity.Receipt;
import com.example.trc.entity.Transaction;
import com.example.trc.repository.FuelRepository;
import com.example.trc.repository.ReceiptRepository;
import com.example.trc.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FuelService {

    private final FuelRepository fuelRepository;
    private final TransactionRepository transactionRepository;
    private final ReceiptRepository receiptRepository;

    @Autowired
    public FuelService(FuelRepository fuelRepository, TransactionRepository transactionRepository, ReceiptRepository receiptRepository) {
        this.fuelRepository = fuelRepository;
        this.transactionRepository = transactionRepository;
        this.receiptRepository = receiptRepository;
    }

    public List<Fuel> getAllFuels() {
        return fuelRepository.findAll();
    }

    public Optional<Fuel> getFuelById(Long id) {
        return fuelRepository.findById(id);
    }

    public List<Fuel> getFuelsByType(String type) {
        return fuelRepository.findByTypeIgnoreCase(type);
    }

    public Fuel saveFuel(Fuel fuel) {
        return fuelRepository.save(fuel);
    }


    public Fuel updateFuel(Long id, Fuel fuel) {
        return fuelRepository.findById(id).map(f -> {
            fuel.setType(f.getType());
            fuel.setPricePerLiter(f.getPricePerLiter());
            fuel.setAvailableLiters(f.getAvailableLiters());
            return fuelRepository.save(f);
        }).orElseThrow(() -> new RuntimeException("Fuel not found"));
    }

    public void deleteFuel(Long id) {
        Fuel fuel = fuelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fuel not found"));
        List<Transaction> transactions = transactionRepository.findTransactionByFuel(fuel);
        receiptRepository.deleteByTransaction(transactions);
        transactionRepository.deleteAll(transactions);
        fuelRepository.deleteById(id);
    }
}
