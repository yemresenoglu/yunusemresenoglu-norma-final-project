package com.example.finalproject.service;

import com.example.finalproject.dto.ExtractResponseDTO;
import com.example.finalproject.dto.MoneyTransferRequestDTO;
import com.example.finalproject.dto.PayingWithDebitCardDTO;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;

public interface TransactionService {

    Collection<ExtractResponseDTO> getExtractOfCardResponseDTO(String cardNumber);

    void moneyTransfer(MoneyTransferRequestDTO moneyTransferRequestDTO) throws IOException;

    void payingWithDebitCard(PayingWithDebitCardDTO payingWithDebitCardDTO) throws ParseException;
}
