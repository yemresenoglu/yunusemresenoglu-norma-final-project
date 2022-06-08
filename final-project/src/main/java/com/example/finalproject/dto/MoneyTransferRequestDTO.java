package com.example.finalproject.dto;

import java.math.BigDecimal;

public record MoneyTransferRequestDTO(String fromAccount,
                                      String toAccount,
                                      BigDecimal amount) {
}
