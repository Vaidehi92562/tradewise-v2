package com.tradewisev2.dto;

public class WatchlistResponse {
    private Long id;
    private String stockSymbol;
    private String companyName;
    private Double currentPrice;

    public WatchlistResponse() {
    }

    public WatchlistResponse(Long id, String stockSymbol, String companyName, Double currentPrice) {
        this.id = id;
        this.stockSymbol = stockSymbol;
        this.companyName = companyName;
        this.currentPrice = currentPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }
}
