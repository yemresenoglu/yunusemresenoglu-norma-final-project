package com.example.finalproject.dto;

import java.math.BigDecimal;

public record PayingWithDebitCardDTO(String cardNumber,
                                     String expiredDate,
                                     String cvv,
                                     BigDecimal amount,
                                     String receiverAccount) {
}
