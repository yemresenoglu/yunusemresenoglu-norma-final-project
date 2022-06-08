package com.example.finalproject.service;

import com.example.finalproject.converter.CurrencyServiceOperationConverter;
import com.example.finalproject.dto.CurrencyDTO;
import com.example.finalproject.dto.GetCurrencyResponseDTO;
import com.example.finalproject.exception.CurrencyServiceOperationException;
import com.example.finalproject.model.Currency;
import com.example.finalproject.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyServiceOperationConverter currencyServiceOperationConverter;


    @Override
    public void addCurrency(CurrencyDTO currencyDTO) {

        Currency currency = currencyServiceOperationConverter.convertTOCurrency(currencyDTO);
        currencyRepository.save(currency);

        log.info("Currency is added.");

    }

    @Override
    public Collection<GetCurrencyResponseDTO> getCurrencies() {

        log.info("All currencies getting.");
        return currencyRepository.findAll().stream().map(currencyServiceOperationConverter::toGetCurrenciesResponse).toList();
    }

    @Override
    public GetCurrencyResponseDTO getCurrency(String code) {

        Currency currency = currencyRepository.findByCode(code);

        if (Objects.isNull(currency)) {
            throw new CurrencyServiceOperationException.CurrencyNotFoundException("Currency not found!");
        }

        return currencyServiceOperationConverter.toGetCurrencyResponse(currency);

    }

    @Override
    public Currency getCurrencyByCode(String code) {

        Currency currency = currencyRepository.findByCode(code);

        if (Objects.isNull(currency)) {
            throw new CurrencyServiceOperationException.CurrencyNotFoundException("Currency not found!");
        }


        return currency;
    }

    @Override
    public void updateName(String code, String name) {

        Currency currency = currencyRepository.findByCode(code);

        if (Objects.isNull(currency)) {
            throw new CurrencyServiceOperationException.CurrencyNotFoundException("Currency not found!");
        } else

            currency.setName(name);
        currencyRepository.save(currency);
        log.info("Currency is updated.");
    }

    @Override
    public void updateNameById(Long id, String name) {

        Currency currency = currencyRepository
                .findById(id)
                .orElseThrow(() -> new CurrencyServiceOperationException.CurrencyNotFoundException("Currency not found!"));
        if (!Objects.isNull(currency)) {
            currency.setName(name);
            currencyRepository.save(currency);
        }


    }

    @Override
    public void hardDeleteCurrencyById(Long id, boolean hardDelete) {

        Currency currency = currencyRepository
                .findById(id)
                .orElseThrow(() -> new CurrencyServiceOperationException.CurrencyNotFoundException("Currency not found!"));
        if (!Objects.isNull(currency) && hardDelete==true) {
            currencyRepository.delete(currency);
            log.info(id + " is deleting!");
        }

    }
}
