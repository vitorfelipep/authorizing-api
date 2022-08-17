package com.test.authorizingapi.domain.resources;

import com.test.authorizingapi.domain.resources.dto.TransactionReponse;
import com.test.authorizingapi.domain.resources.dto.TransactionRequest;
import com.test.authorizingapi.domain.service.AuthorizerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transacoes")
@Api(value = "Transacoes")
public class TransactionsResource {

    private final AuthorizerService authorizerService;

    public TransactionsResource(AuthorizerService authorizerService) {
        this.authorizerService = authorizerService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Inicia uma transação do cartão")
    public ResponseEntity<TransactionReponse> createNewCard(@RequestBody @Valid TransactionRequest transactionRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorizerService.createTransaction(transactionRequest));
    }


}
