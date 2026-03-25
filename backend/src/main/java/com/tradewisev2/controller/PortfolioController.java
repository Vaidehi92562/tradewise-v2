package com.tradewisev2.controller;

import com.tradewisev2.dto.PortfolioResponse;
import com.tradewisev2.service.PortfolioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/portfolio")
@CrossOrigin(origins = "*")
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("/{userId}")
    public PortfolioResponse getPortfolio(@PathVariable Long userId) {
        return portfolioService.getPortfolio(userId);
    }
}
