package com.tradewisev2.controller;

import com.tradewisev2.dto.TradeRequest;
import com.tradewisev2.service.TradeService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/trade")
@CrossOrigin(origins = "*")
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping("/buy")
    public Map<String, String> buyStock(@RequestBody TradeRequest request) {
        return Map.of("message", tradeService.buyStock(request));
    }

    @PostMapping("/sell")
    public Map<String, String> sellStock(@RequestBody TradeRequest request) {
        return Map.of("message", tradeService.sellStock(request));
    }
}
