package com.example.finalproject.repository;

import com.example.finalproject.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    @Query("SELECT Currency FROM Currency WHERE Currency.code=?1")
    Currency findByCode(String code);
}
