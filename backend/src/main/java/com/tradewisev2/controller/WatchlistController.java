package com.tradewisev2.controller;

import com.tradewisev2.dto.WatchlistResponse;
import com.tradewisev2.model.Watchlist;
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

    @PostMapping("/add")
    public Watchlist addToWatchlist(@RequestBody Map<String, String> request) {
        Long userId = Long.parseLong(request.get("userId"));
        String symbol = request.get("symbol");
        return watchlistService.addToWatchlist(userId, symbol);
    }

    @GetMapping("/{userId}")
    public List<WatchlistResponse> getWatchlist(@PathVariable Long userId) {
        return watchlistService.getWatchlistByUserId(userId);
    }

    @DeleteMapping("/remove")
    public Map<String, String> removeFromWatchlist(@RequestParam Long userId, @RequestParam String symbol) {
        watchlistService.removeFromWatchlist(userId, symbol);
        return Map.of("message", "Removed from watchlist");
    }
}
