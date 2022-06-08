package com.example.finalproject.validator;

import com.example.finalproject.dto.CreateAccountDTO;
import com.example.finalproject.exception.AccountServiceException;
import com.example.finalproject.exception.BaseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Qualifier("accountDTO")
@Component
public class AccountDTOValidator implements Validator<CreateAccountDTO> {


    @Override
    public void validate(CreateAccountDTO createAccountDTO) throws BaseException {


        if(Objects.isNull(createAccountDTO.currencyCode())){
            throw new AccountServiceException.AccountNotValidException("Currency code can not be null or empty!");
        }
        if(Objects.isNull(createAccountDTO.branchCode())){
            throw new AccountServiceException.AccountNotValidException("Branch code can not be null or empty!");
        }



    }
}
