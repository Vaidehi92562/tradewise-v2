package com.tradewisev2.service;

import com.tradewisev2.dto.TransactionResponse;
import com.tradewisev2.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionResponse> getTransactionsByUserId(Long userId) {
        return transactionRepository.findByUserIdOrderByTimestampDesc(userId)
                .stream()
                .map(transaction -> TransactionResponse.builder()
                        .stockSymbol(transaction.getStockSymbol())
                        .transactionType(transaction.getTransactionType())
                        .quantity(transaction.getQuantity())
                        .pricePerStock(transaction.getPricePerStock())
                        .totalAmount(transaction.getTotalAmount())
                        .timestamp(transaction.getTimestamp())
                        .build())
                .toList();
    }
}
