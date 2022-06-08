package com.example.finalproject.repository;

import com.example.finalproject.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByIdNumber(String idNumber);

    @Query("SELECT c FROM Customer c WHERE c.isDeleted = ?1")
    Collection<Customer> findAllCustomersByDeleteStatusByJPQL(boolean status);

    @Query("SELECT c FROM Customer c LEFT JOIN Card card ON c.id = card.account.customer.id WHERE c.id = ?1 AND card.cardType = 'CREDIT_CARD' AND card.cardBalance > 0.00 ")
    Customer findBCustomerAccountBalanceMoreThanZeroJPQL(String idNumber);

    @Query("SELECT c FROM Customer c LEFT JOIN Account a ON c.id = a.customer.id WHERE c.id = ?1 AND a.balance > 0.00")
    Customer findBCustomerHaveCreditCardDebitJPQL(String idNumber);

    @Query("SELECT c FROM Customer c LEFT JOIN Account a ON c.id = a.customer.id WHERE a.accountNumber = ?1")
    Customer findCustomerByAccountNumber(String accountNumber);



}
