package com.tradewisev2.controller;

import com.tradewisev2.dto.StockResponse;
import com.tradewisev2.service.WatchlistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/watchlist")
@CrossOrigin(origins = "*")
public class WatchlistController {

    private final WatchlistService watchlistService;

    public WatchlistController(WatchlistService watchlistService) {
        this.watchlistService = watchlistService;
    }

    @GetMapping("/{userId}")
    public List<StockResponse> getWatchlist(@PathVariable Long userId) {
        return watchlistService.getWatchlist(userId);
    }

    @PostMapping("/add")
    public Map<String, String> addToWatchlist(@RequestParam Long userId, @RequestParam String symbol) {
        return Map.of("message", watchlistService.addToWatchlist(userId, symbol));
    }

    @DeleteMapping("/remove")
    public Map<String, String> removeFromWatchlist(@RequestParam Long userId, @RequestParam String symbol) {
        return Map.of("message", watchlistService.removeFromWatchlist(userId, symbol));
    }
}
