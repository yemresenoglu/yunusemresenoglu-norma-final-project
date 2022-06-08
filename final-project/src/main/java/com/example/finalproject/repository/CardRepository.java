package com.example.finalproject.repository;

import com.example.finalproject.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Card findByCardNumber(String cardNumber);

    @Query("SELECT c FROM Card c WHERE c.cardNumber = ?1")
    Card getBalanceByCardNumber(String cardNumber);
}
