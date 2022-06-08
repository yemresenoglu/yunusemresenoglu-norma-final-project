package com.example.finalproject.repository;

import com.example.finalproject.dto.ExtractResponseDTO;
import com.example.finalproject.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Date;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT\n" +
            "*\n" +
            "FROM\n" +
            "transaction\n" +
            "LEFT JOIN card ON transaction.card_id=card.id\n" +
            "WHERE date_part('month', (transaction.created_at)) = date_part('month', (current_date))\n" +
            "AND card.card_number='?1'", nativeQuery = true)
    Collection<Transaction> getExtractOfCardByCardNumberJPQL(String cardNumber);
}
