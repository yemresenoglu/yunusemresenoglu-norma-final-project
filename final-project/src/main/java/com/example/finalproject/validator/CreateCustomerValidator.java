package com.example.finalproject.validator;

import com.example.finalproject.dto.CustomerDTO;
import com.example.finalproject.exception.BaseException;
import com.example.finalproject.exception.CustomerServiceOperationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Qualifier("customerValidator")
@Component
public class CreateCustomerValidator implements Validator<CustomerDTO> {


    @Override
    public void validate(CustomerDTO customerDTO) throws BaseException {

        if (Objects.isNull(customerDTO)) {
            throw new CustomerServiceOperationException.CustomerNotValidException("Customer ınformation cannot be empty or null.");
        }

        if (Objects.isNull(customerDTO.idNumber())) {
            throw new CustomerServiceOperationException.CustomerNotValidException("Customer identity cannot be empty or null.");
        }
        if (Objects.isNull(customerDTO.name())) {
            throw new CustomerServiceOperationException.CustomerNotValidException("Customer name cannot be empty or null.");
        }
        if (Objects.isNull(customerDTO.lastname())) {
            throw new CustomerServiceOperationException.CustomerNotValidException("Customer lastname cannot be empty or null.");
        }
        if (Objects.isNull(customerDTO.birthday())) {
            throw new CustomerServiceOperationException.CustomerNotValidException("Customer birthday cannot be empty or null.");
        }
        if (Objects.isNull(customerDTO.customerContact().firstCallNumber())) {
            throw new CustomerServiceOperationException.CustomerNotValidException("Customer first email cannot be empty or null.");
        }
        if (Objects.isNull(customerDTO.customerContact().firstCallNumber())) {
            throw new CustomerServiceOperationException.CustomerNotValidException("Customer first phone number cannot be empty or null.");
        }
        if (Objects.isNull(customerDTO.gender())) {
            throw new CustomerServiceOperationException.CustomerNotValidException("Customer gender cannot be empty or null.");
        }
        if (Objects.isNull(customerDTO.job())) {
            throw new CustomerServiceOperationException.CustomerNotValidException("Customer job cannot be empty or null.");
        }
        if (Objects.isNull(customerDTO.education())) {
            throw new CustomerServiceOperationException.CustomerNotValidException("Customer education cannot be empty or null.");
        }
        if (Objects.isNull(customerDTO.salary())) {
            throw new CustomerServiceOperationException.CustomerNotValidException("Customer salary cannot be empty or null.");
        }
        if (Objects.isNull(customerDTO.customerAddress().country())) {
            throw new CustomerServiceOperationException.CustomerNotValidException("Customer address country cannot be empty or null.");
        }
        if (Objects.isNull(customerDTO.customerAddress().city())) {
            throw new CustomerServiceOperationException.CustomerNotValidException("Customer address city cannot be empty or null.");
        }
        if (Objects.isNull(customerDTO.customerAddress().postalCode())) {
            throw new CustomerServiceOperationException.CustomerNotValidException("Customer address postal code cannot be empty or null.");
        }
        if (Objects.isNull(customerDTO.customerAddress().description())) {
            throw new CustomerServiceOperationException.CustomerNotValidException("Customer address description cannot be empty or null.");
        }


    }
}
