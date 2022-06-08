package com.example.finalproject.converter;

import com.example.finalproject.dto.CreateAccountDTO;
import com.example.finalproject.model.Account;
import com.example.finalproject.model.AccountType;
import com.example.finalproject.service.CurrencyService;
import com.example.finalproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.example.finalproject.generator.AccountNumberGenerator.accountNumberGenerator;

@Component
@RequiredArgsConstructor
public class AccountServiceOperationConverterImpl implements AccountServiceOperationConverter {

    private final CurrencyService currencyService;
    private final CustomerService customerService;


    @Override
    //for checking account
    public Account toConvertAccount(String idNumber, CreateAccountDTO createAccountDTO) {
        Account account = new Account();

        String accountNumber = accountNumberGenerator(12);
        String bankCode = "10234";

        //Country code + check digit + bank code + reserve field + basic account number field
        String ıban = "TR" + "56" + bankCode + "3" + "0000" + accountNumber;

        account.setCustomer(customerService.getCustomerByCustomerİdNumber(idNumber));
        account.setBankCode(bankCode);//bank code
        account.setBranchCode(createAccountDTO.branchCode());
        account.setAccountNumber(accountNumber);
        account.setCurrency(currencyService.getCurrencyByCode(createAccountDTO.currencyCode()));
        account.setIban(ıban);
        account.setCreatedBy("Yunus Emre Şenoğlu");
        account.setCreatedAt(new Date());
        return account;

    }

    @Override
    //for deposit account
    public Account toConvertDepositAccount(String idNumber, CreateAccountDTO createAccountDTO) {
        Account account = new Account();

        String accountNumber = accountNumberGenerator(12);
        String bankCode = "10234";

        //Country code + check digit + bank code + reserve field + basic account number field
        String ıban = "TR" + "56" + bankCode + "3" + "0000" + accountNumber;

        account.setAccountType(AccountType.DEPOSIT_ACCOUNT);
        account.setCustomer(customerService.getCustomerByCustomerİdNumber(idNumber));
        account.setBankCode(bankCode); //bank code
        account.setBranchCode(createAccountDTO.branchCode());
        account.setAccountNumber(accountNumber);
        account.setCurrency(currencyService.getCurrencyByCode(createAccountDTO.currencyCode()));
        account.setIban(ıban);
        account.setCreatedBy("Yunus Emre Şenoğlu");
        account.setCreatedAt(new Date());
        return account;

    }


}
