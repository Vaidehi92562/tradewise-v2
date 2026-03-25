package com.tradewisev2.service;

import com.tradewisev2.dto.TradeRequest;
import com.tradewisev2.model.*;
import com.tradewisev2.repository.HoldingRepository;
import com.tradewisev2.repository.TransactionRepository;
import com.tradewisev2.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TradeService {

    private final UserService userService;
    private final WalletRepository walletRepository;
    private final HoldingRepository holdingRepository;
    private final TransactionRepository transactionRepository;
    private final StockService stockService;

    public TradeService(UserService userService,
                        WalletRepository walletRepository,
                        HoldingRepository holdingRepository,
                        TransactionRepository transactionRepository,
                        StockService stockService) {
        this.userService = userService;
        this.walletRepository = walletRepository;
        this.holdingRepository = holdingRepository;
        this.transactionRepository = transactionRepository;
        this.stockService = stockService;
    }

    public String buyStock(TradeRequest request) {
        if (request.getQuantity() == null || request.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }

        User user = userService.getUserById(request.getUserId());
        Wallet wallet = walletRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
        Stock stock = stockService.getStockEntityBySymbol(request.getSymbol());

        double totalAmount = stock.getCurrentPrice() * request.getQuantity();

        if (wallet.getBalance() < totalAmount) {
            throw new RuntimeException("Insufficient wallet balance");
        }

        wallet.setBalance(wallet.getBalance() - totalAmount);
        wallet.setUpdatedAt(LocalDateTime.now());
        walletRepository.save(wallet);

        Holding holding = holdingRepository.findByUserIdAndStockSymbol(request.getUserId(), request.getSymbol())
                .orElse(null);

        if (holding == null) {
            holding = Holding.builder()
                    .user(user)
                    .stockSymbol(request.getSymbol())
                    .quantity(request.getQuantity())
                    .averageBuyPrice(stock.getCurrentPrice())
                    .build();
        } else {
            int oldQty = holding.getQuantity();
            double oldAvg = holding.getAverageBuyPrice();
            int newQty = oldQty + request.getQuantity();
            double newAvg = ((oldQty * oldAvg) + (request.getQuantity() * stock.getCurrentPrice())) / newQty;

            holding.setQuantity(newQty);
            holding.setAverageBuyPrice(newAvg);
        }

        holdingRepository.save(holding);

        Transaction transaction = Transaction.builder()
                .user(user)
                .stockSymbol(request.getSymbol())
                .transactionType("BUY")
                .quantity(request.getQuantity())
                .pricePerStock(stock.getCurrentPrice())
                .totalAmount(totalAmount)
                .timestamp(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);

        return "Stock bought successfully";
    }

    public String sellStock(TradeRequest request) {
        if (request.getQuantity() == null || request.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }

        User user = userService.getUserById(request.getUserId());
        Wallet wallet = walletRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
        Stock stock = stockService.getStockEntityBySymbol(request.getSymbol());

        Holding holding = holdingRepository.findByUserIdAndStockSymbol(request.getUserId(), request.getSymbol())
                .orElseThrow(() -> new RuntimeException("Holding not found"));

        if (holding.getQuantity() < request.getQuantity()) {
            throw new RuntimeException("Not enough quantity to sell");
        }

        double totalAmount = stock.getCurrentPrice() * request.getQuantity();

        holding.setQuantity(holding.getQuantity() - request.getQuantity());
        if (holding.getQuantity() == 0) {
            holdingRepository.delete(holding);
        } else {
            holdingRepository.save(holding);
        }

        wallet.setBalance(wallet.getBalance() + totalAmount);
        wallet.setUpdatedAt(LocalDateTime.now());
        walletRepository.save(wallet);

        Transaction transaction = Transaction.builder()
                .user(user)
                .stockSymbol(request.getSymbol())
                .transactionType("SELL")
                .quantity(request.getQuantity())
                .pricePerStock(stock.getCurrentPrice())
                .totalAmount(totalAmount)
                .timestamp(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);

        return "Stock sold successfully";
    }
}
