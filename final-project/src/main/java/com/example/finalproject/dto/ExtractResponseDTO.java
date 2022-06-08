package com.example.finalproject.dto;

import com.example.finalproject.model.TransactionType;

import java.math.BigDecimal;
import java.util.Date;

public record ExtractResponseDTO(String card,
                                 String fromAccount,
                                 String toAccount,
                                 BigDecimal amount,
                                 BigDecimal fee,
                                 BigDecimal totalAmount,
                                 BigDecimal balance,
                                 BigDecimal previousBalance,
                                 TransactionType transactionType,
                                 Date transferDate) {
}
