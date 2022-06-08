package com.example.finalproject.service;

import com.example.finalproject.converter.UserServiceOperationConverter;
import com.example.finalproject.dto.UserRegisterRequestDTO;
import com.example.finalproject.exception.UserServiceOperationException;
import com.example.finalproject.model.User;
import com.example.finalproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserServiceOperationConverter userServiceOperationConverter;


    @Override
    public void createUser(UserRegisterRequestDTO userRegisterRequestDTO) {
        if (!Objects.isNull(userRepository.findByUserName(userRegisterRequestDTO.userName()))) {
            throw new UserServiceOperationException.UserDefinedException("User defined!");
        } else {
            User user = userServiceOperationConverter.userCreateConverter(userRegisterRequestDTO);
            userRepository.save(user);

            log.info("User successfully registered.", HttpStatus.CREATED);
        }

    }
}
