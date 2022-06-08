package com.example.finalproject.validator;

import com.example.finalproject.dto.CreateDebitCardDTO;
import com.example.finalproject.exception.BaseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("debitCardDTO")
@Component
public class CreateDebitCardDTOValidator implements Validator<CreateDebitCardDTO> {
    @Override
    public void validate(CreateDebitCardDTO createDebitCardDTO) throws BaseException {

    }
}
