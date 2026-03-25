package com.tradewisev2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddFundsRequest {
    private Long userId;
    private Double amount;
}
