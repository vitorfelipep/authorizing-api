package com.test.authorizingapi.domain.resources.dto;

import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record BalanceResponse(
    BigDecimal balance
) {

}
