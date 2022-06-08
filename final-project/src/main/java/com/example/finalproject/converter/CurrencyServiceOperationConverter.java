package com.example.finalproject.converter;

import com.example.finalproject.dto.CurrencyDTO;
import com.example.finalproject.dto.GetCurrencyResponseDTO;
import com.example.finalproject.model.Currency;

public interface CurrencyServiceOperationConverter {

    Currency convertTOCurrency(CurrencyDTO currencyDTO);

    GetCurrencyResponseDTO toGetCurrenciesResponse(Currency currency);

    GetCurrencyResponseDTO toGetCurrencyResponse(Currency currency);
}
