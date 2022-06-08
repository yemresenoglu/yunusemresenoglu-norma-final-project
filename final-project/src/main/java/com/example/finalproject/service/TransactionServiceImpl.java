package com.example.finalproject.service;

import com.example.finalproject.converter.StringConvertToDate;
import com.example.finalproject.converter.TransactionServiceOperationConverter;
import com.example.finalproject.dto.ExtractResponseDTO;
import com.example.finalproject.dto.MoneyTransferRequestDTO;
import com.example.finalproject.dto.PayingWithDebitCardDTO;
import com.example.finalproject.exception.AccountServiceException;
import com.example.finalproject.exception.CardServiceOperationException;
import com.example.finalproject.exception.TransactionServiceException;
import com.example.finalproject.model.*;
import com.example.finalproject.repository.AccountRepository;
import com.example.finalproject.repository.CardRepository;
import com.example.finalproject.repository.TransactionRepository;
import com.example.finalproject.controller.CurrencyExternalAPIController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;


@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;
    private final TransactionServiceOperationConverter transactionServiceOperationConverter;
    private final CurrencyExternalAPIController currencyExternalAPIController;

    @Override
    public Collection<ExtractResponseDTO> getExtractOfCardResponseDTO(String cardNumber) {


        return transactionRepository.getExtractOfCardByCardNumberJPQL(cardNumber)
                .stream().map(transactionServiceOperationConverter::getCardExtract).toList();
    }

    @Override
    @Transactional
    public void moneyTransfer(MoneyTransferRequestDTO moneyTransferRequestDTO) throws IOException {
        Account sender = accountRepository.getAccountByAccountNumber(moneyTransferRequestDTO.fromAccount());
        sender.setLockedBalance(sender.getBalance());
        Account receiver = accountRepository.getAccountByAccountNumber(moneyTransferRequestDTO.toAccount());
        receiver.setLockedBalance(receiver.getBalance());

        if (Objects.isNull(sender)) {
            throw new AccountServiceException.AccountNotFoundException("Sender account not found!");
        }
        if (Objects.isNull(receiver)) {
            throw new AccountServiceException.AccountNotFoundException("Receiver Account not found!");
        }
        if (receiver.getAccountStatus() == AccountStatus.LOCKED) {
            throw new AccountServiceException("Receiver account is blocked!");
        }
        if (receiver.getAccountStatus() == AccountStatus.PASSİVE) {
            throw new AccountServiceException("Receiver account is passive!");
        }
        if (sender.getBalance().compareTo(moneyTransferRequestDTO.amount()) == (-1)) {
            throw new TransactionServiceException("Insufficient balance!");
        }

        while (sender.getCustomer().getId() != receiver.getCustomer().getId()) {
            if (sender.getAccountType() == AccountType.DEPOSIT_ACCOUNT) {
                throw new TransactionServiceException("You can not transfer money from deposit account");
            }
            if (receiver.getAccountType() == AccountType.DEPOSIT_ACCOUNT) {
                throw new TransactionServiceException("You can not transfer money to deposit account");
            }
        }

        if (sender.getCurrency().getId() == receiver.getCurrency().getId()) {
            sender.setBalance(sender.getBalance().subtract(moneyTransferRequestDTO.amount()));
            sender.setLockedBalance(BigDecimal.ZERO);

            receiver.setBalance(receiver.getBalance().add(moneyTransferRequestDTO.amount()));
            receiver.setLockedBalance(BigDecimal.ZERO);

        } else {
            sender.setBalance(sender.getBalance().subtract(moneyTransferRequestDTO.amount()));
            sender.setLockedBalance(BigDecimal.ZERO);

            receiver.setBalance(receiver.getBalance().add(moneyTransferRequestDTO.amount().multiply(currencyExternalAPIController.getCurrency(sender.getCurrency().getCode(), receiver.getCurrency().getCode()))));
            receiver.setLockedBalance(BigDecimal.ZERO);

        }

        accountRepository.save(sender);
        accountRepository.save(receiver);

        transactionRepository.save(transactionServiceOperationConverter.toConvertTransaction(moneyTransferRequestDTO));

        log.info("Transfer is completed successfully.");
    }

    @Override
    @Transactional
    public void payingWithDebitCard(PayingWithDebitCardDTO payingWithDebitCardDTO) throws ParseException {

        Card payingCard = cardRepository.findByCardNumber(payingWithDebitCardDTO.cardNumber());
        Account account = accountRepository.getAccountByCardNumber(payingWithDebitCardDTO.cardNumber());

        account.setLockedBalance(account.getBalance());

        if (Objects.isNull(payingCard)) {
            throw new CardServiceOperationException("Card not found!");
        }
        if (!payingWithDebitCardDTO.cvv().equals(payingCard.getCvv())) {
            throw new CardServiceOperationException.CardInvalıdException("Invalid cvv number!");
        }
        if (StringConvertToDate.stringToConvertDate(payingWithDebitCardDTO.expiredDate()).before(new Date())) {
            throw new CardServiceOperationException.CardInvalıdException("Card is expired!");
        }
        if (payingCard.getCardBalance().compareTo(payingWithDebitCardDTO.amount()) != 1) {
            throw new CardServiceOperationException("Insufficient balance.");
        }
        if (payingCard.getCardType() == CardType.DEBIT_CARD) {
            account.setBalance(account.getBalance().subtract(payingWithDebitCardDTO.amount()));
            account.setLockedBalance(BigDecimal.ZERO);
            payingCard.setCardBalance(payingCard.getCardBalance().add(payingWithDebitCardDTO.amount()));
            payingCard.setCardLimit(payingCard.getCardLimit().subtract(payingWithDebitCardDTO.amount()));
        }


        accountRepository.save(account);
        cardRepository.save(payingCard);

        transactionRepository.save(transactionServiceOperationConverter.toConvertDebitCardPaying(payingWithDebitCardDTO));
    }


}
