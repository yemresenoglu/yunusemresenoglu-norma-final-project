package com.example.finalproject.controller;

import com.example.finalproject.dto.CreateAccountDTO;
import com.example.finalproject.service.AccountService;
import com.example.finalproject.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final Validator<CreateAccountDTO> accountDTOValidator;
    private final Validator<String> customerIdNumberValidator;
    private final Validator<String> accountNumberValidator;


    //create CHECKING_ACCOUNT
    @PostMapping("/createChecking")
    public ResponseEntity<?> createCheckingAccount(@RequestParam(name = "customerIdNumber", required = true) String idNumber,
                                                   @RequestBody CreateAccountDTO createAccountDTO) {

        customerIdNumberValidator.validate(idNumber);
        accountDTOValidator.validate(createAccountDTO);
        accountService.createAccount(idNumber, createAccountDTO);

        return ResponseEntity.ok().build();
    }

    //crete DEPOSIT_ACCOUNT
    @PostMapping("/createDeposit")
    public ResponseEntity<?> createDepositAccount(@RequestParam(name = "customerIdNumber", required = true) String idNumber,
                                                  @RequestBody CreateAccountDTO createAccountDTO) {

        customerIdNumberValidator.validate(idNumber);
        accountDTOValidator.validate(createAccountDTO);
        accountService.createDepositAccount(idNumber, createAccountDTO);

        return ResponseEntity.ok().build();
    }


    //hard delete account
    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<?> hardDeleteAccount(@PathVariable String accountNumber,
                                               @RequestParam(name = "hardDelete") boolean hardDelete
    ) {

        accountNumberValidator.validate(accountNumber);
        accountService.deleteAccount(accountNumber, hardDelete);

        return ResponseEntity.ok().build();
    }

    //soft delete account
    @PutMapping("/{accountNumber}")
    public ResponseEntity<?> softDeleteAccount(@PathVariable String accountNumber,
                                               @RequestParam(name = "softDelete") boolean softDelete
    ) {

        accountNumberValidator.validate(accountNumber);
        accountService.softDeleteAccount(accountNumber, softDelete);

        return ResponseEntity.ok().build();
    }

}
