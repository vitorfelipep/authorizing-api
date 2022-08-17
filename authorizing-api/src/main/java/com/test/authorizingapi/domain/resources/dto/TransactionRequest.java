package com.test.authorizingapi.domain.resources.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
public record  TransactionRequest(
    @NotNull Long numeroCartao,
    @NotBlank String senhaCartao,
    @NotNull BigDecimal valor
) {
}
