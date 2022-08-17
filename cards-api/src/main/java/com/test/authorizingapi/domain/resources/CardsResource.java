package com.test.authorizingapi.domain.resources;

import com.test.authorizingapi.domain.resources.dto.BalanceResponse;
import com.test.authorizingapi.domain.resources.dto.CardReponse;
import com.test.authorizingapi.domain.resources.dto.CardRequest;
import com.test.authorizingapi.domain.resources.dto.DebtRequest;
import com.test.authorizingapi.domain.service.CardsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/cartoes")
@Api(value = "Cards")
public class CardsResource {

    private final CardsService cardsService;

    public CardsResource(CardsService cardsService) {
        this.cardsService = cardsService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Inicia a criação de um novo cartão")
    public ResponseEntity<CardReponse> createNewCard(@RequestBody @Valid CardRequest cardRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cardsService.createCard(cardRequest));
    }

    @GetMapping(value = "/{cardNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BalanceResponse> findBalanceOfCard(
        @PathVariable Long cardNumber
    ) {
        return ResponseEntity.ok(cardsService.getBalanceCard(cardNumber));
    }

    @PostMapping(value = "/{cardNumber}/check", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> checkInfo(
        @PathVariable Long cardNumber,
        @RequestBody @Valid CardRequest cardRequest
    ) {
        return ResponseEntity.ok(cardsService.checkInfo(cardNumber, cardRequest));
    }

    @PatchMapping(value = "/{cardNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> patchBalanceOfCard(
            @PathVariable Long cardNumber,
            @RequestBody @Valid DebtRequest debtRequest
    ) {
        cardsService.patchBalance(cardNumber, debtRequest.balance());
        return ResponseEntity.status(HttpStatus.CREATED).body("OK");
    }

}
