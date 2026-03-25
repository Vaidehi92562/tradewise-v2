package com.tradewisev2.service;

import com.tradewisev2.model.Stock;
import com.tradewisev2.repository.StockRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class StockSimulationService {

    private final StockRepository stockRepository;

    public StockSimulationService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Scheduled(fixedRate = 1000)
    public void updateStockPrices() {
        List<Stock> stocks = stockRepository.findAll();

        for (Stock stock : stocks) {
            double currentPrice = stock.getCurrentPrice();
            double randomDelta = ThreadLocalRandom.current().nextDouble(-0.8, 0.8);
            double newPrice = Math.max(1.0, currentPrice + randomDelta);

            stock.setPreviousPrice(currentPrice);
            stock.setCurrentPrice(newPrice);

            if (stock.getDayHigh() == null || newPrice > stock.getDayHigh()) {
                stock.setDayHigh(newPrice);
            }

            if (stock.getDayLow() == null || newPrice < stock.getDayLow()) {
                stock.setDayLow(newPrice);
            }

            stock.setUpdatedAt(LocalDateTime.now());
        }

        stockRepository.saveAll(stocks);
    }
}
