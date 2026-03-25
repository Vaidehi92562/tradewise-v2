package com.tradewisev2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradeRequest {
    private Long userId;
    private String symbol;
    private Integer quantity;
}
