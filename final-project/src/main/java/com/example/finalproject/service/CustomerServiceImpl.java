package com.example.finalproject.service;

import com.example.finalproject.converter.CustomerServiceOperationConverter;
import com.example.finalproject.dto.CustomerDTO;
import com.example.finalproject.dto.CustomerUpdateDTO;
import com.example.finalproject.dto.GetCustomerResponseDTO;
import com.example.finalproject.exception.CurrencyServiceOperationException;
import com.example.finalproject.exception.CustomerServiceOperationException;
import com.example.finalproject.model.Customer;
import com.example.finalproject.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerServiceOperationConverter customerServiceOperationConverter;

    @Override//create customer
    public void createCustomer(CustomerDTO customerDTO) {

        Customer customer = customerServiceOperationConverter.createCustomerConverter(customerDTO);

        if (!Objects.isNull(customerRepository.findByIdNumber(customerDTO.idNumber()))) {
            throw new CustomerServiceOperationException.CustomerNotSavedException(customerDTO.idNumber() + " There is a customer with this id number.");
        }


        customerRepository.save(customer);
        log.info("Customer saved." + customer.getFullName());
    }

    @Override//Get all customers
    public Collection<GetCustomerResponseDTO> getAllCustomers() {

        return customerRepository.findAllCustomersByDeleteStatusByJPQL(false).stream().map(customerServiceOperationConverter::getAllCustomers).toList();
    }

    @Override //customer soft delete
    public void softDelete(String idNumber, boolean softDelete) {

        Customer customer = customerRepository.findByIdNumber(idNumber);

        if (Objects.isNull(customerRepository.findByIdNumber(idNumber))) {
            throw new CurrencyServiceOperationException.CurrencyNotFoundException("Customer not found!");
        }
        if (customer.isDeleted() == true) {
            throw new CustomerServiceOperationException.CustomerAlreadyDeletedException("Customer already deleted.");
        }
        // control of customer's account balance
        if (!Objects.isNull(customerRepository.findBCustomerAccountBalanceMoreThanZeroJPQL(idNumber))) {
            throw new CustomerServiceOperationException.CustomerAccountBalanceMoreThanZeroException("You can not " + idNumber + " because account balance more than zero.");
        }
        //control of customer's credit card debit
        if (!Objects.isNull(customerRepository.findBCustomerHaveCreditCardDebitJPQL(idNumber))) {
            throw new CustomerServiceOperationException.CustomerCreditCardDebitMoreThanZeroException("You can not " + idNumber + " because credit card debit more than zero.");
        }


        if (softDelete) {

            customer.setDeleted(true);
            customerRepository.save(customer);
        }

        log.info(customer.getIdNumber() + " is soft deleted.");
    }


    @Override//Customer hard delete
    public void hardDelete(String idNumber, boolean hardDelete) {

        Customer customer = customerRepository.findByIdNumber(idNumber);

        if (Objects.isNull(customerRepository.findByIdNumber(idNumber))) {
            throw new CurrencyServiceOperationException.CurrencyNotFoundException("Customer not found!");
        }
        if (customer.isDeleted() == true) {
            throw new CustomerServiceOperationException.CustomerAlreadyDeletedException("Customer already deleted.");
        }
        if (!Objects.isNull(customerRepository.findBCustomerAccountBalanceMoreThanZeroJPQL(idNumber))) {
            throw new CustomerServiceOperationException.CustomerAccountBalanceMoreThanZeroException("You can not delete " + idNumber + " because account balance more than zero.");
        }
        if (!Objects.isNull(customerRepository.findBCustomerHaveCreditCardDebitJPQL(idNumber))) {
            throw new CustomerServiceOperationException.CustomerCreditCardDebitMoreThanZeroException("You can not delete " + idNumber + " because credit card debit more than zero.");
        }

        if (hardDelete == true) {
            customerRepository.delete(customer);
            log.info(customer.getIdNumber() + "is hard deleted!");
        }

    }

    @Override//update customer
    public void updateCustomer(String idNumber, boolean update, CustomerUpdateDTO customerUpdateDTO) {

        Customer customer = customerRepository.findByIdNumber(idNumber);

        if (Objects.isNull(customerRepository.findByIdNumber(idNumber))) {
            throw new CurrencyServiceOperationException.CurrencyNotFoundException("Customer not found!");
        }
        if (update == true) {
            customer.setUpdatedAt(new Date());
            customer.setIdNumber(customerUpdateDTO.idNumber());
            customer.setName(customerUpdateDTO.name());
            customer.setLastname(customerUpdateDTO.lastname());

            customerRepository.save(customer);

            log.info("Customer updated!");
        }
    }

    @Override
    public Customer getCustomerByCustomerİdNumber(String idNumber) {
        Customer customer = customerRepository.findByIdNumber(idNumber);
        if (Objects.isNull(customer)) {
            throw new CustomerServiceOperationException.CustomerNotFoundException("Customer not found!");
        }

        return customer;
    }

    @Override
    public String getCardHolderNameByAccountNumber(String accountNumber) {

        Customer customer = customerRepository.findCustomerByAccountNumber(accountNumber);
        if(Objects.isNull(customer))
        {
            throw new CustomerServiceOperationException.CustomerNotFoundException("Customer not found!");
        }


        return customer.getFullName();
    }


}
