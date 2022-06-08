package com.example.finalproject.service;

import com.example.finalproject.dto.CreateAccountDTO;
import com.example.finalproject.model.Account;

import java.math.BigDecimal;

public interface AccountService {

    void createAccount(String idNumber, CreateAccountDTO createAccountDTO);

    Account getAccountHolderByAccountNumber(String accountNumber);

    void createDepositAccount(String idNumber, CreateAccountDTO createAccountDTO);

    void deleteAccount(String accountNumber, boolean hardDelete);

    void softDeleteAccount(String accountNumber, boolean softDelete);

    Account getAccountByCustomerIdNumber(String idNumber);

    String getAccountHolderFullNameByAccountNumber(String accountNumber);

    BigDecimal getBalanceByAccountNumber(String fromAccount);

    Account getAccountByCardNumber(String cardNumber);
}
