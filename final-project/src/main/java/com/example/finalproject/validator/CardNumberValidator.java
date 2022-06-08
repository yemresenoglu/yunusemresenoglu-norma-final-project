package com.example.finalproject.validator;

import com.example.finalproject.exception.BaseException;
import com.example.finalproject.exception.CardServiceOperationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Pattern;

@Qualifier("cardNumber")
@Component
public class CardNumberValidator implements Validator<String>{


    @Override
    public void validate(String cardNumber) throws BaseException {

        if (!checkCardNumberRegex(cardNumber))
        {
            throw new CardServiceOperationException.CardInvalıdException("Invalid card number!");
        }
        if (Objects.isNull(cardNumber))
        {
            throw new CardServiceOperationException.CardInvalıdException("Invalid card number!");
        }

    }



    public static boolean checkCardNumberRegex(String cardNumber)
    {
        String regexPattern = "^4[0-9]{12}(?:[0-9]{3})?$";

        return Pattern.compile(regexPattern)
                .matcher(cardNumber)
                .matches();
    }
}
