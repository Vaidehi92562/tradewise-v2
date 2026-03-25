package com.tradewisev2.service;

import com.tradewisev2.dto.WatchlistResponse;
import com.tradewisev2.model.Stock;
import com.tradewisev2.model.User;
import com.tradewisev2.model.Watchlist;
import com.tradewisev2.repository.StockRepository;
import com.tradewisev2.repository.UserRepository;
import com.tradewisev2.repository.WatchlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchlistService {

    private final WatchlistRepository watchlistRepository;
    private final UserRepository userRepository;
    private final StockRepository stockRepository;

    public WatchlistService(WatchlistRepository watchlistRepository,
                            UserRepository userRepository,
                            StockRepository stockRepository) {
        this.watchlistRepository = watchlistRepository;
        this.userRepository = userRepository;
        this.stockRepository = stockRepository;
    }

    public Watchlist addToWatchlist(Long userId, String symbol) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String normalizedSymbol = symbol == null ? "" : symbol.trim().toUpperCase();

        Stock stock = stockRepository.findBySymbol(normalizedSymbol)
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        boolean alreadyExists = watchlistRepository.findByUserId(userId).stream()
                .anyMatch(item -> item.getStockSymbol() != null
                        && item.getStockSymbol().equalsIgnoreCase(normalizedSymbol));

        if (alreadyExists) {
            throw new RuntimeException("Stock already in watchlist");
        }

        Watchlist watchlist = new Watchlist();
        watchlist.setUser(user);
        watchlist.setStockSymbol(stock.getSymbol());

        return watchlistRepository.save(watchlist);
    }

    public List<WatchlistResponse> getWatchlistByUserId(Long userId) {
        List<Watchlist> items = watchlistRepository.findByUserId(userId);

        return items.stream().map(item -> {
            Stock stock = stockRepository.findBySymbol(item.getStockSymbol()).orElse(null);

            String companyName = stock != null ? stock.getCompanyName() : item.getStockSymbol();
            Double currentPrice = stock != null ? stock.getCurrentPrice() : 0.0;

            return new WatchlistResponse(
                    item.getId(),
                    item.getStockSymbol(),
                    companyName,
                    currentPrice
            );
        }).collect(Collectors.toList());
    }

    public void removeFromWatchlist(Long userId, String symbol) {
        String normalizedSymbol = symbol == null ? "" : symbol.trim().toUpperCase();

        List<Watchlist> items = watchlistRepository.findByUserId(userId);

        Watchlist watchlist = items.stream()
                .filter(item -> item.getStockSymbol() != null
                        && item.getStockSymbol().equalsIgnoreCase(normalizedSymbol))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Stock not found in watchlist"));

        watchlistRepository.delete(watchlist);
    }
}
