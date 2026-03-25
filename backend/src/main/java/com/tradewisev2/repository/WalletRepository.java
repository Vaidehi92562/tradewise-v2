package com.tradewisev2.repository;

import com.tradewisev2.model.User;
import com.tradewisev2.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByUser(User user);
    Optional<Wallet> findByUserId(Long userId);
}
