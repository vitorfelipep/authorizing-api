package com.test.authorizingapi.domain.resources.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
public record CardRequest(
    @NotNull Long numeroCartao,
    @NotBlank String senha
) implements Serializable {}
