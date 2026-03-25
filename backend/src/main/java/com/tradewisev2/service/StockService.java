package com.tradewisev2.service;

import com.tradewisev2.dto.StockResponse;
import com.tradewisev2.model.Stock;
import com.tradewisev2.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<StockResponse> getAllStocks() {
        return stockRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public StockResponse getStockBySymbol(String symbol) {
        Stock stock = stockRepository.findBySymbol(symbol)
                .orElseThrow(() -> new RuntimeException("Stock not found"));
        return mapToResponse(stock);
    }

    public List<StockResponse> getTopMovers() {
        return stockRepository.findAll()
                .stream()
                .sorted(Comparator.comparingDouble(this::calculateChangePercent).reversed())
                .limit(5)
                .map(this::mapToResponse)
                .toList();
    }

    public Stock getStockEntityBySymbol(String symbol) {
        return stockRepository.findBySymbol(symbol)
                .orElseThrow(() -> new RuntimeException("Stock not found"));
    }

    private StockResponse mapToResponse(Stock stock) {
        return StockResponse.builder()
                .symbol(stock.getSymbol())
                .companyName(stock.getCompanyName())
                .currentPrice(stock.getCurrentPrice())
                .previousPrice(stock.getPreviousPrice())
                .previousClose(stock.getPreviousClose())
                .dayHigh(stock.getDayHigh())
                .dayLow(stock.getDayLow())
                .changePercent(calculateChangePercent(stock))
                .build();
    }

    private double calculateChangePercent(Stock stock) {
        if (stock.getPreviousClose() == null || stock.getPreviousClose() == 0) {
            return 0.0;
        }
        return ((stock.getCurrentPrice() - stock.getPreviousClose()) / stock.getPreviousClose()) * 100;
    }
}
