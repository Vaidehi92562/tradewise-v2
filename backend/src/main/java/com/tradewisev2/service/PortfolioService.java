package com.tradewisev2.service;

import com.tradewisev2.dto.PortfolioResponse;
import com.tradewisev2.model.Holding;
import com.tradewisev2.model.Stock;
import com.tradewisev2.repository.HoldingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortfolioService {

    private final HoldingRepository holdingRepository;
    private final StockService stockService;

    public PortfolioService(HoldingRepository holdingRepository, StockService stockService) {
        this.holdingRepository = holdingRepository;
        this.stockService = stockService;
    }

    public PortfolioResponse getPortfolio(Long userId) {
        List<Holding> holdings = holdingRepository.findByUserId(userId);
        List<PortfolioResponse.HoldingItem> holdingItems = new ArrayList<>();

        double totalInvested = 0.0;
        double totalCurrentValue = 0.0;

        for (Holding holding : holdings) {
            Stock stock = stockService.getStockEntityBySymbol(holding.getStockSymbol());

            double investedValue = holding.getAverageBuyPrice() * holding.getQuantity();
            double currentValue = stock.getCurrentPrice() * holding.getQuantity();
            double profitLoss = currentValue - investedValue;

            totalInvested += investedValue;
            totalCurrentValue += currentValue;

            holdingItems.add(PortfolioResponse.HoldingItem.builder()
                    .stockSymbol(holding.getStockSymbol())
                    .quantity(holding.getQuantity())
                    .averageBuyPrice(holding.getAverageBuyPrice())
                    .currentPrice(stock.getCurrentPrice())
                    .investedValue(investedValue)
                    .currentValue(currentValue)
                    .profitLoss(profitLoss)
                    .build());
        }

        return PortfolioResponse.builder()
                .totalInvested(totalInvested)
                .currentValue(totalCurrentValue)
                .totalProfitLoss(totalCurrentValue - totalInvested)
                .holdings(holdingItems)
                .build();
    }
}
