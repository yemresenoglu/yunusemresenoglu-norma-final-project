package com.example.finalproject.validator;

import com.example.finalproject.dto.CurrencyDTO;
import com.example.finalproject.exception.BaseException;
import com.example.finalproject.exception.CurrencyServiceOperationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Qualifier("currencyDTO")
@Component
public class CurrencyValidator implements Validator<CurrencyDTO> {

    @Override
    public void validate(CurrencyDTO currencyDTO) throws BaseException {

        if (Objects.isNull(currencyDTO.name())) {
            throw new CurrencyServiceOperationException.CurrencyNotValidException("Currency name can not be null or empty.");
        }
        if (Objects.isNull(currencyDTO.code())) {
            throw new CurrencyServiceOperationException.CurrencyNotValidException("Currency code can not be null or empty.");
        }

    }
}
