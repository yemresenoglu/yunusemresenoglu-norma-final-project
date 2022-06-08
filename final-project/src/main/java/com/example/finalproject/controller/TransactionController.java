package com.example.finalproject.controller;


import com.example.finalproject.dto.MoneyTransferRequestDTO;
import com.example.finalproject.dto.PayingWithDebitCardDTO;
import com.example.finalproject.service.TransactionService;
import com.example.finalproject.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final Validator<MoneyTransferRequestDTO> moneyTransferRequestDTOValidator;
    private final Validator<PayingWithDebitCardDTO> payingWithDebitCardDTOValidator;
    private final TransactionService transactionService;

    //money transfer from one account to other account;
    @PostMapping("/transferMoney")
    public ResponseEntity<?> moneyTransfer(@RequestBody MoneyTransferRequestDTO moneyTransferRequestDTO) throws IOException {
        moneyTransferRequestDTOValidator.validate(moneyTransferRequestDTO);
        transactionService.moneyTransfer(moneyTransferRequestDTO);

        return ResponseEntity.ok().build();
    }

    //paying by debit card
    @PostMapping("/paying")
    public ResponseEntity<?> PayingWithDebitCard(@RequestBody PayingWithDebitCardDTO payingWithDebitCardDTO) throws ParseException {

        payingWithDebitCardDTOValidator.validate(payingWithDebitCardDTO);
        transactionService.payingWithDebitCard(payingWithDebitCardDTO);

        return ResponseEntity.ok().build();
    }


}
