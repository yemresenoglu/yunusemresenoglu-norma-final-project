package com.example.finalproject.converter;

import com.example.finalproject.dto.CurrencyDTO;
import com.example.finalproject.dto.GetCurrencyResponseDTO;
import com.example.finalproject.model.Currency;
import org.springframework.stereotype.Component;

@Component
public class CurrencyServiceOperationConverterImpl implements CurrencyServiceOperationConverter {


    @Override
    public Currency convertTOCurrency(CurrencyDTO currencyDTO) {
        Currency currency = new Currency();

        currency.setName(currencyDTO.name());
        currency.setCode(currencyDTO.code());
        currency.setSign(currencyDTO.sign());

        return currency;
    }

    @Override
    public GetCurrencyResponseDTO toGetCurrenciesResponse(Currency currency) {
        return new GetCurrencyResponseDTO(currency.getName(), currency.getCode(), currency.getSign());
    }

    @Override
    public GetCurrencyResponseDTO toGetCurrencyResponse(Currency currency) {
        return new GetCurrencyResponseDTO(currency.getName(), currency.getCode(), currency.getSign());
    }
}
