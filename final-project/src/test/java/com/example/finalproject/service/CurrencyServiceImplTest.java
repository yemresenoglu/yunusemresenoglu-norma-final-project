package com.example.finalproject.service;

import com.example.finalproject.converter.CurrencyServiceOperationConverter;
import com.example.finalproject.dto.GetCurrencyResponseDTO;
import com.example.finalproject.model.Currency;
import com.example.finalproject.repository.CurrencyRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class CurrencyServiceImplTest {

    private CurrencyServiceImpl currencyServiceImpl;
    private CurrencyRepository currencyRepository;
    private CurrencyServiceOperationConverter currencyServiceOperationConverter;

    @Before
    public void setUp() throws Exception {

        currencyRepository = Mockito.mock(CurrencyRepository.class);
        currencyServiceOperationConverter = Mockito.mock(CurrencyServiceOperationConverter.class);

        currencyServiceImpl = new CurrencyServiceImpl(currencyRepository, currencyServiceOperationConverter);
    }

    @Test
    public void whenGetCurrencyCalledWithValidRequest_itShouldReturnValidRequestDTO()
    {
        String code = generateCurrencyCode();
        Currency currency = generateCurrency();
        GetCurrencyResponseDTO getCurrencyResponseDTO = generateCurrencyResponseDTO();

        //Determine mock service behavior regarding test scenario
        Mockito.when(currencyServiceImpl.getCurrency("TRY")).thenReturn(getCurrencyResponseDTO);
        Mockito.when(currencyRepository.findByCode(code)).thenReturn(currency);

        GetCurrencyResponseDTO result = currencyServiceImpl.getCurrency(code);

        Assert.assertEquals(result,getCurrencyResponseDTO);

        Mockito.verify(currencyServiceImpl).getCurrency(code);
        Mockito.verify(currencyRepository).findByCode(code);

    }

    private GetCurrencyResponseDTO generateCurrencyResponseDTO() {
        GetCurrencyResponseDTO getCurrencyResponseDTO = new GetCurrencyResponseDTO("Turkish Lira","TRY","₺" );

        return getCurrencyResponseDTO;
    }

    private Currency generateCurrency() {

        return Currency.builder().code("TRY").name("Turkish Lira").sign("₺").build();
    }

    private String generateCurrencyCode() {

        String code = "TRY";
        return code;

    }
}