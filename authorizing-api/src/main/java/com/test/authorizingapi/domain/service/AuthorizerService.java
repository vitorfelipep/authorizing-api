package com.test.authorizingapi.domain.service;

import com.test.authorizingapi.domain.repository.AuthorizerRepository;
import com.test.authorizingapi.domain.resources.dto.*;
import com.test.authorizingapi.domain.service.exception.InsufficientFundsException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class AuthorizerService {

    private final AuthorizerRepository authorizerRepository;

    private final String cardUrl = "http://localhost:8080/";

    public AuthorizerService(AuthorizerRepository authorizerRepository) {
        this.authorizerRepository = authorizerRepository;
    }


    public TransactionReponse createTransaction(TransactionRequest transactionRequest) {
        RestTemplate restTemplate = new RestTemplate();
        String checkInfo
                = cardUrl.concat("/" + transactionRequest.numeroCartao() + "/check");
        CardRequest cardRequest = CardRequest.builder()
                .numeroCartao(transactionRequest.numeroCartao())
                .senha(transactionRequest.senhaCartao())
                .build();
        ResponseEntity<Boolean> response
                = restTemplate.postForEntity(checkInfo, cardRequest, Boolean.class);

        if (response.getBody()) {
            String balanceUrl
                    = cardUrl.concat("/" + transactionRequest.numeroCartao());
            ResponseEntity<BalanceResponse> balanceResponseEntity = restTemplate.getForEntity(balanceUrl, BalanceResponse.class);
            if (balanceResponseEntity.getBody() != null) {
                BigDecimal balance = balanceResponseEntity.getBody().getBalance();
                if (balance.compareTo(transactionRequest.valor()) > 0) {
                    BigDecimal updateBalance = balance.min(transactionRequest.valor());
                    String pathSuccess = restTemplate.patchForObject(balanceUrl.concat("?debt=".concat(String.valueOf(updateBalance.doubleValue()))),
                            DebtRequest.builder().balance(updateBalance).build(), String.class);
                     return new TransactionReponse(pathSuccess);
                } else {
                    throw new InsufficientFundsException("Saldo insuficiente!");

                }
            }
        }
        return null;
    }
}
