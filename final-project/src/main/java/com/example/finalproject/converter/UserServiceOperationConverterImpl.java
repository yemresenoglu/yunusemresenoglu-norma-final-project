package com.example.finalproject.converter;

import com.example.finalproject.dto.UserRegisterRequestDTO;
import com.example.finalproject.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class UserServiceOperationConverterImpl implements UserServiceOperationConverter {

    private final PasswordEncoder passwordEncoder;

    @Override
    public User userCreateConverter(UserRegisterRequestDTO userRegisterRequestDTO) {

        User user = new User();
        user.setUserName(userRegisterRequestDTO.userName());
        user.setPassword(passwordEncoder.encode(userRegisterRequestDTO.password()));
        user.setBirthday(userRegisterRequestDTO.birthday());
        user.setEmail(userRegisterRequestDTO.email());
        user.setCallNumber(userRegisterRequestDTO.callNumber());
        user.setUserType(userRegisterRequestDTO.userType());

        user.setCreatedAt(new Date());
        user.setCreatedBy("YES");


        return user;
    }
}
