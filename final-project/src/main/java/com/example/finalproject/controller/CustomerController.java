package com.example.finalproject.controller;

import com.example.finalproject.dto.CustomerDTO;
import com.example.finalproject.dto.CustomerUpdateDTO;
import com.example.finalproject.dto.GetCustomerResponseDTO;
import com.example.finalproject.service.CustomerService;
import com.example.finalproject.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final Validator<CustomerDTO> createCustomerValidator;
    private final Validator<String> customerIdNumberValidator;

    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO customerDTO) {
        createCustomerValidator.validate(customerDTO);
        customerService.createCustomer(customerDTO);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<GetCustomerResponseDTO>> getCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PutMapping("/{idNumber}")
    public ResponseEntity<?> softDeleteCustomer(@PathVariable String idNumber,
                                                @RequestParam(name = "softDelete", required = false) boolean softDelete) {


        customerIdNumberValidator.validate(idNumber);
        customerService.softDelete(idNumber, softDelete);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idNumber}")
    public ResponseEntity<?> hardDeleteCustomer(@PathVariable String idNumber,
                                                @RequestParam(name = "hardDelete", required = true) boolean hardDelete) {


        customerIdNumberValidator.validate(idNumber);
        customerService.hardDelete(idNumber, hardDelete);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/update/{idNumber}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCustomer(@PathVariable String idNumber,
                                            @RequestParam(name = "update", required = true) boolean update,
                                            @Valid @ModelAttribute("customer") CustomerUpdateDTO customerUpdateDTO) {


        customerIdNumberValidator.validate(idNumber);
        customerService.updateCustomer(idNumber, update, customerUpdateDTO);

        return ResponseEntity.ok().build();
    }


}
