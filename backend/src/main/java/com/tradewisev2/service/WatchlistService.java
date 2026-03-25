package com.tradewisev2.service;

import com.tradewisev2.dto.StockResponse;
import com.tradewisev2.model.User;
import com.tradewisev2.model.Watchlist;
import com.tradewisev2.repository.WatchlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService {

    private final WatchlistRepository watchlistRepository;
    private final UserService userService;
    private final StockService stockService;

    public WatchlistService(WatchlistRepository watchlistRepository,
                            UserService userService,
                            StockService stockService) {
        this.watchlistRepository = watchlistRepository;
        this.userService = userService;
        this.stockService = stockService;
    }

    public List<StockResponse> getWatchlist(Long userId) {
        return watchlistRepository.findByUserId(userId)
                .stream()
                .map(item -> stockService.getStockBySymbol(item.getStockSymbol()))
                .toList();
    }

    public String addToWatchlist(Long userId, String symbol) {
        User user = userService.getUserById(userId);

        boolean exists = watchlistRepository.findByUserIdAndStockSymbol(userId, symbol).isPresent();
        if (exists) {
            throw new RuntimeException("Stock already in watchlist");
        }

        watchlistRepository.save(
                Watchlist.builder()
                        .user(user)
                        .stockSymbol(symbol)
                        .build()
        );

        return "Stock added to watchlist";
    }

    public String removeFromWatchlist(Long userId, String symbol) {
        Watchlist item = watchlistRepository.findByUserIdAndStockSymbol(userId, symbol)
                .orElseThrow(() -> new RuntimeException("Stock not found in watchlist"));

        watchlistRepository.delete(item);
        return "Stock removed from watchlist";
    }
}
