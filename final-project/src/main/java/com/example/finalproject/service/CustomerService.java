package com.example.finalproject.service;

import com.example.finalproject.dto.CustomerDTO;
import com.example.finalproject.dto.CustomerUpdateDTO;
import com.example.finalproject.dto.GetCustomerResponseDTO;
import com.example.finalproject.model.Customer;

import java.util.Collection;


public interface CustomerService {

    void createCustomer(CustomerDTO customerDTO);

    Collection<GetCustomerResponseDTO> getAllCustomers();

    void softDelete(String idNumber, boolean softDelete);

    void hardDelete(String idNumber, boolean hardDelete);

    void updateCustomer(String idNumber, boolean update, CustomerUpdateDTO customerUpdateDTO);

    Customer getCustomerByCustomerİdNumber(String idNumber);


    String getCardHolderNameByAccountNumber(String accountNumber);
}
