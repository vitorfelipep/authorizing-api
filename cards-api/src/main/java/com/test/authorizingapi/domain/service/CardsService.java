package com.test.authorizingapi.domain.service;

import com.test.authorizingapi.domain.resources.dto.BalanceResponse;
import com.test.authorizingapi.domain.resources.dto.CardReponse;
import com.test.authorizingapi.domain.resources.dto.CardRequest;
import com.test.authorizingapi.domain.entity.Card;
import com.test.authorizingapi.domain.repository.CardRepository;
import com.test.authorizingapi.domain.service.exception.CardAlreadyExistsException;
import com.test.authorizingapi.domain.service.exception.CardNotFoundException;
import com.test.authorizingapi.domain.service.exception.InvalidPassWordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
public class CardsService {

    private final CardRepository cardRepository;

    public CardsService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public CardReponse createCard(CardRequest cardRequest) {
        Optional<Card> optionalCard = cardRepository.findById(cardRequest.numeroCartao());
        if (optionalCard.isPresent()) {
            throw new CardAlreadyExistsException("Já existe um cartão com essa numeração!");
        }

        Card card = Card.builder()
                .cardNumber(cardRequest.numeroCartao())
                .password(cardRequest.senha())
                .balance(addNewBalance())
                .status(false).build();
        cardRepository.save(card);

        return CardReponse.builder()
                .numeroCartao(cardRequest.numeroCartao())
                .senha(cardRequest.senha())
                .build();
    }



    private BigDecimal addNewBalance() {
        return BigDecimal.valueOf(500);
    }


    public BalanceResponse getBalanceCard(Long cardNumber) {
        Card card = cardRepository.findById(cardNumber).orElseThrow(() -> new CardNotFoundException("Cartão não encontrado."));
        return new BalanceResponse(card.getBalance());
    }

    public void patchBalance(Long cardNumber, BigDecimal debt) {
        Card card = cardRepository.findById(cardNumber).orElseThrow(() -> new CardNotFoundException("Cartão não encontrado."));
        card.setBalance(debt);
        cardRepository.save(card);
    }

    public Boolean checkInfo(Long cardNumber, CardRequest cardRequest) {
        Card card = cardRepository.findById(cardNumber).orElseThrow(() -> new CardNotFoundException("Cartão não encontrado."));
        if(!card.getPassword().equals(cardRequest.senha())) {
            throw new InvalidPassWordException("Senha não inválida!");
        }
        return true;
    }
}
