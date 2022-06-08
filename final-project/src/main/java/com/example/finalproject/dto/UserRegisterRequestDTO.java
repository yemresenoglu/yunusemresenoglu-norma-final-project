package com.example.finalproject.dto;

import com.example.finalproject.model.UserType;

import java.util.Date;

public record UserRegisterRequestDTO(String userName,
                                     String password,
                                     Date birthday,
                                     String email,
                                     String callNumber,
                                     UserType userType) {
}
