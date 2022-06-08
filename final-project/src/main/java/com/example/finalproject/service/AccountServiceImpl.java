package com.example.finalproject.service;

import com.example.finalproject.converter.AccountServiceOperationConverter;
import com.example.finalproject.dto.CreateAccountDTO;
import com.example.finalproject.exception.AccountServiceException;
import com.example.finalproject.model.Account;
import com.example.finalproject.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountServiceOperationConverter accountServiceOperationConverter;


    public void createAccount(String idNumber, CreateAccountDTO createAccountDTO) {

        Account account = accountServiceOperationConverter.toConvertAccount(idNumber, createAccountDTO);
        accountRepository.save(account);
        log.info("Account created.");
    }

    @Override
    public Account getAccountHolderByAccountNumber(String accountNumber) {
        Account account = accountRepository.getAccountHolderNameByAccountNumberJPA(accountNumber);
        if (Objects.isNull(account)) {
            throw new RuntimeException("Account not found!");
        }
        return account;
    }

    @Override
    public void createDepositAccount(String idNumber, CreateAccountDTO createAccountDTO) {

        Account account = accountServiceOperationConverter.toConvertDepositAccount(idNumber, createAccountDTO);

        accountRepository.save(account);
        log.info("Account created.");

    }

    @Override
    public void deleteAccount(String accountNumber, boolean hardDelete) {

        Account account = accountRepository.getAccountByAccountNumber(accountNumber);
        if (Objects.isNull(account)) {
            throw new AccountServiceException.AccountNotFoundException("Account not found!");
        }
        if (Objects.isNull(accountRepository.getAccountByAccountBalanceGreaterThanZeroJPA(accountNumber))) {
            throw new AccountServiceException.AccountBalanceGraterThanZeroException("Account balance is greater than zero!");
        }

        if (hardDelete) {

            accountRepository.delete(account);
            log.info("Account is hard deleted!");
        }

    }

    @Override
    public void softDeleteAccount(String accountNumber, boolean softDelete) {


        Account account = accountRepository.getAccountByAccountNumber(accountNumber);
        if (Objects.isNull(account)) {
            throw new AccountServiceException.AccountNotFoundException("Account not found!");
        }
        if (Objects.isNull(accountRepository.getAccountByAccountBalanceGreaterThanZeroJPA(accountNumber))) {
            throw new AccountServiceException.AccountBalanceGraterThanZeroException("Account balance is greater than zero!");
        }

        if (softDelete) {
            account.setDeleted(true);
            accountRepository.save(account);
            log.info("Account is soft deleted!");
        }


    }

    @Override
    public Account getAccountByCustomerIdNumber(String idNumber) {
        Account account = accountRepository.getAccountByCustomerIdNumber(idNumber);

        return account;
    }

    @Override
    public String getAccountHolderFullNameByAccountNumber(String accountNumber) {
        Account account = accountRepository.getAccountHolderNameByAccountNumberJPA(accountNumber);
        if (Objects.isNull(account)) {
            throw new AccountServiceException.AccountNotFoundException("Account not found!");
        }

        return account.getCustomer().getFullName();
    }

    @Override
    public BigDecimal getBalanceByAccountNumber(String fromAccount) {
        return accountRepository.getBalanceByAccountNumber(fromAccount);
    }

    @Override
    public Account getAccountByCardNumber(String cardNumber) {
        Account account = accountRepository.getAccountByCardNumber(cardNumber);

        return account;
    }
}
