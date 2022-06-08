package com.example.finalproject.controller;

import com.example.finalproject.dto.CardBalanceResponseDTO;
import com.example.finalproject.dto.CreateCreditCardDTO;
import com.example.finalproject.dto.CreateDebitCardDTO;
import com.example.finalproject.dto.ExtractResponseDTO;
import com.example.finalproject.service.CardService;
import com.example.finalproject.service.TransactionService;
import com.example.finalproject.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cards")
public class CardController {


    private final CardService cardService;
    private final TransactionService transactionService;
    @Qualifier("debitCardDTO")
    private final Validator<CreateDebitCardDTO> createDebitCardDTOValidator;
    @Qualifier("creditCardDTO")
    private final Validator<CreateCreditCardDTO> creditCardDTOValidator;
    @Qualifier("cardNumber")
    private final Validator<String> cardNumberValidator;


    @PostMapping("/debit/create")
    public ResponseEntity<?> createDebitCard(@RequestBody CreateDebitCardDTO createDebitCardDTO,
                                             @RequestParam(name = "accountNumber") String accountNumber) {


        createDebitCardDTOValidator.validate(createDebitCardDTO);
        cardService.createDebitCard(accountNumber, createDebitCardDTO);

        return ResponseEntity.ok().build();
    }


    @PostMapping("/credit/create")
    public ResponseEntity<?> createCreditCard(@RequestBody CreateCreditCardDTO createCreditCardDTO,
                                              @RequestParam(name = "accountNumber") String accountNumber) {

        creditCardDTOValidator.validate(createCreditCardDTO);
        cardService.createCreditCard(accountNumber, createCreditCardDTO);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{cardNumber}")
    public ResponseEntity<?> toChangeCardStatus(@PathVariable String cardNumber,
                                                @RequestParam(name = "cardStatus", required = true) boolean cardBlocked) {

        cardNumberValidator.validate(cardNumber);
        cardService.updateCardStatus(cardNumber, cardBlocked);

        return ResponseEntity.ok().build();
    }

    //get card balance/debit
    @GetMapping("/{cardNumber}")
    public ResponseEntity<CardBalanceResponseDTO> getCardBalance(@PathVariable String cardNumber) {

        cardNumberValidator.validate(cardNumber);

        return ResponseEntity.ok(cardService.getCardBalance(cardNumber));

    }

    //extract of card
    @GetMapping("/{cardNumber}extract")
    public ResponseEntity<Collection<ExtractResponseDTO>> getExtractOfCard(@PathVariable String cardNumber) {

        cardNumberValidator.validate(cardNumber);

        return ResponseEntity.ok(transactionService.getExtractOfCardResponseDTO(cardNumber));

    }




}
