package com.tradewisev2.controller;

import com.tradewisev2.dto.TransactionResponse;
import com.tradewisev2.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{userId}")
    public List<TransactionResponse> getTransactions(@PathVariable Long userId) {
        return transactionService.getTransactionsByUserId(userId);
    }
}
