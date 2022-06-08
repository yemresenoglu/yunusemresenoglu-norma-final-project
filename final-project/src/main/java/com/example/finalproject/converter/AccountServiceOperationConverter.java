package com.example.finalproject.converter;

import com.example.finalproject.dto.CreateAccountDTO;
import com.example.finalproject.model.Account;

public interface AccountServiceOperationConverter {
    Account toConvertAccount(String idNumber, CreateAccountDTO createAccountDTO);

    Account toConvertDepositAccount(String idNumber, CreateAccountDTO createAccountDTO);
}
