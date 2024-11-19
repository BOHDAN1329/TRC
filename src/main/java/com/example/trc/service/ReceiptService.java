package com.example.trc.service;

import com.example.trc.entity.Receipt;
import com.example.trc.entity.Transaction;
import com.example.trc.repository.ReceiptRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final TransactionService transactionService;

    @Autowired
    public ReceiptService(ReceiptRepository receiptRepository, TransactionService transactionService) {
        this.receiptRepository = receiptRepository;
        this.transactionService = transactionService;
    }

    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    public Optional<Receipt> getReceiptById(Long id) {
        return receiptRepository.findById(id);
    }

    public Receipt generateReceipt(Long transactionId) {
        Transaction transaction = transactionService.getTransactionById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        Receipt receipt = new Receipt();
        receipt.setTransaction(transaction);
        receipt.setReceiptDetails("Receipt for transaction ID: " + transactionId +
                "\nClient: " + transaction.getClient().getName() +
                "\nFuel: " + transaction.getFuel().getType() +
                "\nQuantity: " + transaction.getQuantity() + " liters" +
                "\nTotal Price: " + transaction.getTotalPrice() + " USD");

        return receiptRepository.save(receipt);
    }

    public void deleteReceipt(Long id) {
        receiptRepository.deleteById(id);
    }
}
