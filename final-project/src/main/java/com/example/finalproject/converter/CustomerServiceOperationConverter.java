package com.example.finalproject.converter;

import com.example.finalproject.dto.CustomerDTO;
import com.example.finalproject.dto.GetCustomerResponseDTO;
import com.example.finalproject.model.Customer;

public interface CustomerServiceOperationConverter {

    Customer createCustomerConverter(CustomerDTO customerDTO);

    GetCustomerResponseDTO getAllCustomers(Customer customer);
}
