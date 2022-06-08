package com.example.finalproject.service;


import com.example.finalproject.dto.CurrencyDTO;
import com.example.finalproject.dto.GetCurrencyResponseDTO;
import com.example.finalproject.model.Currency;

import java.util.Collection;


public interface CurrencyService {

    void addCurrency(CurrencyDTO currencyDTO);

    Collection<GetCurrencyResponseDTO> getCurrencies();

    GetCurrencyResponseDTO getCurrency(String code);

    Currency getCurrencyByCode(String code);

    void updateName(String code, String name);

    void updateNameById(Long id, String name);

    void hardDeleteCurrencyById(Long id, boolean hardDelete);
}
