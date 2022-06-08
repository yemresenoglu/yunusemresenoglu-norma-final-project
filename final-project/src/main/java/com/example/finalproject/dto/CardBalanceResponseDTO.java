package com.example.finalproject.dto;

import java.math.BigDecimal;
import java.util.Date;

public record CardBalanceResponseDTO(String holderName,
                                     String cardNumber,
                                     Date expiryDate,
                                     int cvv,
                                     BigDecimal cardLimit,
                                     BigDecimal balance) {
}
