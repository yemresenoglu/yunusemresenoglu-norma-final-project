package com.example.finalproject.validator;

import com.example.finalproject.exception.BaseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("currencyCode")
@Component
public class CurrencyCodeValidator implements Validator<String> {


    @Override
    public void validate(String input) throws BaseException {

    }
}
