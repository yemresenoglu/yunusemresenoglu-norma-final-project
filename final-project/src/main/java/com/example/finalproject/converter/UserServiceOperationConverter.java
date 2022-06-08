package com.example.finalproject.converter;

import com.example.finalproject.dto.UserRegisterRequestDTO;
import com.example.finalproject.model.User;

public interface UserServiceOperationConverter {
    User userCreateConverter(UserRegisterRequestDTO userRegisterRequestDTO);
}
