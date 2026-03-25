package com.tradewisev2.controller;

import com.tradewisev2.dto.StockResponse;
import com.tradewisev2.service.StockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@CrossOrigin(origins = "*")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public List<StockResponse> getAllStocks() {
        return stockService.getAllStocks();
    }

    @GetMapping("/{symbol}")
    public StockResponse getStock(@PathVariable String symbol) {
        return stockService.getStockBySymbol(symbol);
    }

    @GetMapping("/movers/top")
    public List<StockResponse> getTopMovers() {
        return stockService.getTopMovers();
    }
}
