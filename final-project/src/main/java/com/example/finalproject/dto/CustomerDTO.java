package com.example.finalproject.dto;


import com.example.finalproject.model.Gender;

import java.math.BigDecimal;
import java.util.Date;

public record CustomerDTO(String idNumber,
                          String name,
                          String lastname,
                          Date birthday,
                          Gender gender,
                          String education,
                          String job,
                          BigDecimal salary,
                          CustomerAddressDTO customerAddress,
                          CustomerContactDTO customerContact,
                          CreateAccountDTO account) {
}
