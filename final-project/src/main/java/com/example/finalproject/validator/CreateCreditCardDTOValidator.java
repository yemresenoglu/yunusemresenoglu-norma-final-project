package com.example.finalproject.validator;

import com.example.finalproject.dto.CreateCreditCardDTO;
import com.example.finalproject.exception.BaseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("creditCardDTO")
@Component
public class CreateCreditCardDTOValidator implements Validator<CreateCreditCardDTO> {
    @Override
    public void validate(CreateCreditCardDTO createCreditCardDTO) throws BaseException {

    }
}
