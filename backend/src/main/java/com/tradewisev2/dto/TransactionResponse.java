package com.tradewisev2.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {
    private String stockSymbol;
    private String transactionType;
    private Integer quantity;
    private Double pricePerStock;
    private Double totalAmount;
    private LocalDateTime timestamp;
}
