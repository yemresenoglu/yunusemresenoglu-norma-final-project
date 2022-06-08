package com.example.finalproject.service;

import com.example.finalproject.converter.CardServiceOperationConverter;
import com.example.finalproject.dto.CardBalanceResponseDTO;
import com.example.finalproject.dto.CreateCreditCardDTO;
import com.example.finalproject.dto.CreateDebitCardDTO;
import com.example.finalproject.exception.CardServiceOperationException;
import com.example.finalproject.model.Card;
import com.example.finalproject.model.CardStatus;
import com.example.finalproject.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardServiceImpl implements CardService{

    private final CardRepository cardRepository;
    private final CardServiceOperationConverter cardServiceOperationConverter;

    @Override
    public void createDebitCard(String accountNumber, CreateDebitCardDTO createDebitCardDTO) {

        Card debitCard = cardServiceOperationConverter.createDebitCard(accountNumber, createDebitCardDTO);
        cardRepository.save(debitCard);
        log.info("Debit card created.");

    }

    @Override
    public void createCreditCard(String accountNumber, CreateCreditCardDTO createCreditCardDTO) {
        Card creditCard = cardServiceOperationConverter.createCreditCard(accountNumber, createCreditCardDTO);
        cardRepository.save(creditCard);
        log.info("Credit card created.");
    }

    @Override
    public void updateCardStatus(String cardNumber, boolean blockedCard) {
        Card card = cardRepository.findByCardNumber(cardNumber);
        if (Objects.isNull(card))
        {
            throw new CardServiceOperationException.CardInvalıdException("Card not found!");
        }
        if(blockedCard) {
            card.setCardStatus(CardStatus.BLOCKED);
            cardRepository.save(card);
            log.info("Credit card is blocked.");
        }

    }

    @Override
    public CardBalanceResponseDTO getCardBalance(String cardNumber) {
        return cardServiceOperationConverter.convertToCardBalanceDTO(cardRepository.getBalanceByCardNumber(cardNumber));
    }


}
