package com.example.finalproject.controller;

import com.example.finalproject.dto.CurrencyDTO;
import com.example.finalproject.dto.GetCurrencyResponseDTO;
import com.example.finalproject.service.CurrencyService;
import com.example.finalproject.validator.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    private final Validator<CurrencyDTO> currencyDTOValidator;

    private final Validator<String> currencyCodeValidator;

    private final Validator<Long> currencyIdValidator;

    public CurrencyController(CurrencyService currencyService, @Qualifier("currencyDTO") Validator<CurrencyDTO> currencyDTOValidator, @Qualifier("currencyCode") Validator<String> currencyCodeValidator, @Qualifier("currencyID") Validator<Long> currencyIdValidator) {
        this.currencyService = currencyService;
        this.currencyDTOValidator = currencyDTOValidator;
        this.currencyCodeValidator = currencyCodeValidator;
        this.currencyIdValidator = currencyIdValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCurrency(@RequestBody CurrencyDTO currencyDTO) {

        currencyDTOValidator.validate(currencyDTO);
        currencyService.addCurrency(currencyDTO);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<GetCurrencyResponseDTO>> getCurrencies() {
        return ResponseEntity.ok(currencyService.getCurrencies());
    }

    @GetMapping("/{code}")
    public ResponseEntity<GetCurrencyResponseDTO> getCurrency(@PathVariable String code) {

        currencyCodeValidator.validate(code);
        return ResponseEntity.ok(currencyService.getCurrency(code));
    }

    @PutMapping("/{code}")
    public ResponseEntity<?> updateCurrencyNameByCode(@PathVariable String code,
                                                      @RequestParam(name = "name", required = true) String name) {

        currencyCodeValidator.validate(code);
        currencyService.updateName(code, name);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCurrencyNameById(@PathVariable Long id,
                                                    @RequestParam(name = "name", required = true) String name) {

        currencyIdValidator.validate(id);
        currencyService.updateNameById(id, name);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> hardDeleteCurrencyById(@PathVariable Long id,
                                                    @RequestParam(name = "hardDelete", required = false) boolean hardDelete) {
        currencyIdValidator.validate(id);
        currencyService.hardDeleteCurrencyById(id, hardDelete);
        return ResponseEntity.ok().build();
    }


}




