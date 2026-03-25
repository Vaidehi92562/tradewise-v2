package com.tradewisev2.service;

import com.tradewisev2.dto.AddFundsRequest;
import com.tradewisev2.model.Transaction;
import com.tradewisev2.model.User;
import com.tradewisev2.model.Wallet;
import com.tradewisev2.repository.TransactionRepository;
import com.tradewisev2.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WalletService {

    private static final double MAX_WALLET_LIMIT = 100000.0;

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final UserService userService;

    public WalletService(WalletRepository walletRepository,
                         TransactionRepository transactionRepository,
                         UserService userService) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
        this.userService = userService;
    }

    public Wallet getWalletByUserId(Long userId) {
        return walletRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    public Wallet addFunds(AddFundsRequest request) {
        if (request.getAmount() == null || request.getAmount() <= 0) {
            throw new RuntimeException("Amount must be greater than 0");
        }

        User user = userService.getUserById(request.getUserId());
        Wallet wallet = getWalletByUserId(request.getUserId());

        double newBalance = wallet.getBalance() + request.getAmount();

        if (newBalance > MAX_WALLET_LIMIT) {
            throw new RuntimeException("Maximum wallet balance allowed is ₹1,00,000");
        }

        wallet.setBalance(newBalance);
        wallet.setUpdatedAt(LocalDateTime.now());
        Wallet updatedWallet = walletRepository.save(wallet);

        Transaction transaction = Transaction.builder()
                .user(user)
                .stockSymbol("WALLET")
                .transactionType("FUND_ADD")
                .quantity(1)
                .pricePerStock(request.getAmount())
                .totalAmount(request.getAmount())
                .timestamp(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);

        return updatedWallet;
    }
}
