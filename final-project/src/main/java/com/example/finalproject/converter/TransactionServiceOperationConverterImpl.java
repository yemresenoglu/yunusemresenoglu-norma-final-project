package com.example.finalproject.converter;

import com.example.finalproject.dto.ExtractResponseDTO;
import com.example.finalproject.dto.MoneyTransferRequestDTO;
import com.example.finalproject.dto.PayingWithDebitCardDTO;
import com.example.finalproject.model.Transaction;
import com.example.finalproject.model.TransactionStatus;
import com.example.finalproject.model.TransactionType;
import com.example.finalproject.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class TransactionServiceOperationConverterImpl implements TransactionServiceOperationConverter {

    private final AccountService accountService;
    @Override
    public ExtractResponseDTO getCardExtract(Transaction transaction) {
        return new ExtractResponseDTO(transaction.getCard().getCardNumber(), transaction.getFromAccount().getAccountNumber(), transaction.getToAccount().getAccountNumber(),
                transaction.getAmount(), transaction.getFee(), transaction.getTotalAmount(), transaction.getBalance(), transaction.getPreviousBalance(), transaction.getTransactionType(),
                transaction.getTransferDate());
    }

    @Override
    //convert to transaction model from money transfer request
    public Transaction toConvertTransaction(MoneyTransferRequestDTO moneyTransferRequestDTO) {
        Transaction transaction = new Transaction();

        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setFromAccount(accountService.getAccountHolderByAccountNumber(moneyTransferRequestDTO.fromAccount()));
        transaction.setToAccount(accountService.getAccountHolderByAccountNumber(moneyTransferRequestDTO.toAccount()));
        transaction.setAmount(moneyTransferRequestDTO.amount());
        if (transaction.getFee().compareTo(BigDecimal.ZERO) == 1)
        {
            transaction.setTotalAmount(moneyTransferRequestDTO.amount().add(transaction.getFee()));
        }else
        {
            transaction.setTotalAmount(moneyTransferRequestDTO.amount());
        }
        transaction.setPreviousBalance(transaction.getBalance());
        transaction.setBalance(transaction.getBalance().subtract(accountService.getBalanceByAccountNumber(moneyTransferRequestDTO.fromAccount())));

        transaction.setTransferDate(new Date());


        return transaction;
    }

    @Override
    public Transaction toConvertDebitCardPaying(PayingWithDebitCardDTO payingWithDebitCardDTO) {

        Transaction transaction = new Transaction();

        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setFromAccount(accountService.getAccountByCardNumber(payingWithDebitCardDTO.cardNumber()));
        transaction.setToAccount(accountService.getAccountHolderByAccountNumber(payingWithDebitCardDTO.receiverAccount()));
        transaction.setAmount(payingWithDebitCardDTO.amount());
        if (transaction.getFee().compareTo(BigDecimal.ZERO) == 1)
        {
            transaction.setTotalAmount(payingWithDebitCardDTO.amount().add(transaction.getFee()));
        }else
        {
            transaction.setTotalAmount(payingWithDebitCardDTO.amount());
        }
        transaction.setPreviousBalance(transaction.getBalance());
        transaction.setBalance(transaction.getPreviousBalance().subtract(payingWithDebitCardDTO.amount()));

        transaction.setTransferDate(new Date());
        return transaction;
    }
}
