package com.tradewisev2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockResponse {
    private String symbol;
    private String companyName;
    private Double currentPrice;
    private Double previousPrice;
    private Double previousClose;
    private Double dayHigh;
    private Double dayLow;
    private Double changePercent;
}
