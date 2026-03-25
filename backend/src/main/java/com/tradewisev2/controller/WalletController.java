package com.tradewisev2.controller;

import com.tradewisev2.dto.AddFundsRequest;
import com.tradewisev2.model.Wallet;
import com.tradewisev2.service.WalletService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
@CrossOrigin(origins = "*")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/{userId}")
    public Wallet getWallet(@PathVariable Long userId) {
        return walletService.getWalletByUserId(userId);
    }

    @PostMapping("/add-funds")
    public Wallet addFunds(@RequestBody AddFundsRequest request) {
        return walletService.addFunds(request);
    }
}
