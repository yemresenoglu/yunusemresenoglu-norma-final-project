package com.example.finalproject.converter;

import com.example.finalproject.dto.CustomerDTO;
import com.example.finalproject.dto.GetCustomerResponseDTO;
import com.example.finalproject.generator.CustomerNumberGenerator;
import com.example.finalproject.model.Customer;
import com.example.finalproject.model.CustomerAddress;
import com.example.finalproject.model.CustomerContact;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class CustomerServiceOperationConverterImpl implements CustomerServiceOperationConverter {


    @Override
    public Customer createCustomerConverter(CustomerDTO customerDTO) {

        Customer customer = new Customer();

        customer.setIdNumber(String.valueOf(customerDTO.idNumber()));
        //customer number length 11 because turkish ıdentification number length 11.
        customer.setCustomerNumber(CustomerNumberGenerator.customerNumberGenerator(11));
        customer.setName(customerDTO.name());
        customer.setLastname(customerDTO.lastname());
        customer.setBirthday(customerDTO.birthday());
        customer.setGender(customerDTO.gender());
        customer.setJob(customerDTO.job());
        customer.setEducation(customerDTO.education());
        customer.setSalary(customerDTO.salary());
        customer.setCreatedAt(new Date());
        customer.setCreatedBy("Yunus Emre Şenoğlu");

        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setCountry(customerDTO.customerAddress().country());
        customerAddress.setCity(customerDTO.customerAddress().city());
        customerAddress.setPostalCode(customerDTO.customerAddress().postalCode());
        customerAddress.setDescription(customerDTO.customerAddress().description());
        customerAddress.setCustomer(customer);

        customer.addAddress(customerAddress);

        CustomerContact customerContact = new CustomerContact();
        customerContact.setFirstEmail(customerDTO.customerContact().firstEmail());
        customerContact.setSecondEmail(customerDTO.customerContact().secondEmail());
        customerContact.setFirstCallNumber(customerDTO.customerContact().firstCallNumber());
        customerContact.setSecondCallNumber(customerDTO.customerContact().secondCallNumber());
        customerContact.setCustomer(customer);
        customer.setCustomerContact(customerContact);


        return customer;


    }

    @Override
    public GetCustomerResponseDTO getAllCustomers(Customer customer) {
        return new GetCustomerResponseDTO(customer.getIdNumber(), customer.getName(), customer.getLastname(),customer.getGender(), customer.getEducation(), customer.getJob(), customer.getSalary());
    }
}
