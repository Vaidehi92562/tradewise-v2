package com.tradewisev2.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "stocks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String symbol;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "current_price", nullable = false)
    private Double currentPrice;

    @Column(name = "previous_price")
    private Double previousPrice;

    @Column(name = "previous_close")
    private Double previousClose;

    @Column(name = "day_high")
    private Double dayHigh;

    @Column(name = "day_low")
    private Double dayLow;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
