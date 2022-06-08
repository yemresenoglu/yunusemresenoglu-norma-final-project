package com.example.finalproject.converter;

import com.example.finalproject.dto.CardBalanceResponseDTO;
import com.example.finalproject.dto.CreateCreditCardDTO;
import com.example.finalproject.dto.CreateDebitCardDTO;
import com.example.finalproject.generator.CardCVVGenerator;
import com.example.finalproject.generator.CardExpireDateGenerator;
import com.example.finalproject.generator.CardNumberGenerator;
import com.example.finalproject.model.Account;
import com.example.finalproject.model.Card;
import com.example.finalproject.service.AccountService;
import com.example.finalproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.example.finalproject.model.CardType.CREDIT_CARD;

@Component
@RequiredArgsConstructor
public class CardServiceOperationConverterImpl implements CardServiceOperationConverter {

    private final CustomerService customerService;
    private final AccountService accountService;


    @Override
    public Card createDebitCard(String accountNumber, CreateDebitCardDTO createDebitCardDTO) {
        Account account = accountService.getAccountHolderByAccountNumber(accountNumber);

        Card debitCard = new Card();

        debitCard.setCreatedAt(new Date());
        debitCard.setCreatedBy("Yunus Emre Şenoğlu");

        debitCard.setCardNumber(CardNumberGenerator.cardNumberGenerator());
        debitCard.setExpiryDate(CardExpireDateGenerator.expireDateGenerator());
        debitCard.setCvv(CardCVVGenerator.cvvGenerator());

        debitCard.setHolderName(customerService.getCardHolderNameByAccountNumber(accountNumber));
        debitCard.setCardLimit(createDebitCardDTO.cardLimit());
        debitCard.setAccount(account);

        account.addCard(debitCard);


        return debitCard;
    }

    @Override
    public Card createCreditCard(String accountNumber, CreateCreditCardDTO createCreditCardDTO) {

        Account account = accountService.getAccountHolderByAccountNumber(accountNumber);

        Card creditCard = new Card();

        creditCard.setCreatedAt(new Date());
        creditCard.setCreatedBy("Yunus Emre Şenoğlu");
        creditCard.setCardType(CREDIT_CARD);

        creditCard.setCardNumber(CardNumberGenerator.cardNumberGenerator());
        creditCard.setExpiryDate(CardExpireDateGenerator.expireDateGenerator());
        creditCard.setCvv(CardCVVGenerator.cvvGenerator());

        creditCard.setHolderName(customerService.getCardHolderNameByAccountNumber(accountNumber));
        creditCard.setCardLimit(createCreditCardDTO.cardLimit());
        creditCard.setAccount(account);

        account.addCard(creditCard);


        return creditCard;
    }

    @Override
    public CardBalanceResponseDTO convertToCardBalanceDTO(Card card) {
        return new CardBalanceResponseDTO(card.getHolderName(), card.getCardNumber(), card.getExpiryDate(), card.getCvv(), card.getCardLimit(), card.getCardBalance());
    }
}
