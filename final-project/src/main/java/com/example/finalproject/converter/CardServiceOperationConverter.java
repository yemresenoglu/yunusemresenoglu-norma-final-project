package com.example.finalproject.converter;

import com.example.finalproject.dto.CardBalanceResponseDTO;
import com.example.finalproject.dto.CreateCreditCardDTO;
import com.example.finalproject.dto.CreateDebitCardDTO;
import com.example.finalproject.model.Card;

public interface CardServiceOperationConverter {
    
    Card createDebitCard(String accountNumber, CreateDebitCardDTO createDebitCardDTO);

    Card createCreditCard(String accountNumber, CreateCreditCardDTO createCreditCardDTO);

    CardBalanceResponseDTO convertToCardBalanceDTO(Card balanceByCardNumber);
}
