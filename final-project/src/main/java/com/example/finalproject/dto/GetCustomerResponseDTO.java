package com.example.finalproject.dto;

import com.example.finalproject.model.Gender;

import java.math.BigDecimal;

public record GetCustomerResponseDTO(String idNumber,
                                     String name,
                                     String lastname,
                                     Gender gender,
                                     String education,
                                     String job,
                                     BigDecimal salary
                                     ) {
}
