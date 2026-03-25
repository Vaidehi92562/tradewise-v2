package com.tradewisev2.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioResponse {
    private Double totalInvested;
    private Double currentValue;
    private Double totalProfitLoss;
    private List<HoldingItem> holdings;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class HoldingItem {
        private String stockSymbol;
        private Integer quantity;
        private Double averageBuyPrice;
        private Double currentPrice;
        private Double investedValue;
        private Double currentValue;
        private Double profitLoss;
    }
}
