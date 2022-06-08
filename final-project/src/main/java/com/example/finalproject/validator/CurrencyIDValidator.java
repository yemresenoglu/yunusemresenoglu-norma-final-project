package com.example.finalproject.validator;

import com.example.finalproject.exception.BaseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("currencyID")
@Component
public class CurrencyIDValidator implements Validator<Long> {
    @Override
    public void validate(Long input) throws BaseException {

    }
}
