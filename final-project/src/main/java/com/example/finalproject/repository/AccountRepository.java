package com.example.finalproject.repository;

import com.example.finalproject.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a.customer FROM Account a WHERE a.accountNumber=?1")
    Account getAccountHolderNameByAccountNumberJPA(String accountNumber);

    Account getAccountByAccountNumber(String accountNumber);

    @Query("SELECT a.customer FROM Account a WHERE a.accountNumber=?1 and a.balance > 0.00")
    Account getAccountByAccountBalanceGreaterThanZeroJPA(String accountNumber);

    @Query("SELECT a FROM Account a LEFT JOIN Customer c ON a.customer.idNumber = c.idNumber WHERE c.idNumber=?1")
    Account getAccountByCustomerIdNumber(String idNumber);

    @Query("SELECT a.balance FROM Account a WHERE a.accountNumber=?1")
    BigDecimal getBalanceByAccountNumber(String fromAccount);

    @Query("SELECT a FROM Account a LEFT JOIN Card  c ON c.account.id=a.id WHERE c.cardNumber=?1")
    Account getAccountByCardNumber(String cardNumber);
}
