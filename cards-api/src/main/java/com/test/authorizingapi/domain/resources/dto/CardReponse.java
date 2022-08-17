package com.test.authorizingapi.domain.resources.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record CardReponse(
    Long numeroCartao,
    String senha
) implements Serializable { }
