package com.example.finalproject.converter;

import com.example.finalproject.dto.ExtractResponseDTO;
import com.example.finalproject.dto.MoneyTransferRequestDTO;
import com.example.finalproject.dto.PayingWithDebitCardDTO;
import com.example.finalproject.model.Transaction;

public interface TransactionServiceOperationConverter {
    ExtractResponseDTO getCardExtract(Transaction transaction);

    Transaction toConvertTransaction(MoneyTransferRequestDTO moneyTransferRequestDTO);

    Transaction toConvertDebitCardPaying(PayingWithDebitCardDTO payingWithDebitCardDTO);
}
