package com.example.finalproject.service;

import com.example.finalproject.dto.CardBalanceResponseDTO;
import com.example.finalproject.dto.CreateCreditCardDTO;
import com.example.finalproject.dto.CreateDebitCardDTO;
import com.example.finalproject.model.Card;

public interface CardService {

    void createDebitCard(String accountNumber, CreateDebitCardDTO createDebitCardDTO);

    void createCreditCard(String accountNumber, CreateCreditCardDTO createCreditCardDTO);

    void updateCardStatus(String cardNumber, boolean cardBlocked);

    CardBalanceResponseDTO getCardBalance(String cardNumber);
}
