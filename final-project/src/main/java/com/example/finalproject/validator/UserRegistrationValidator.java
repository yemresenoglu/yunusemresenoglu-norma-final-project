package com.example.finalproject.validator;

import com.example.finalproject.dto.UserRegisterRequestDTO;
import com.example.finalproject.exception.BaseException;
import com.example.finalproject.exception.UserServiceOperationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.regex.Pattern;

import static com.example.finalproject.validator.EmailValidator.checkEmail;

@Qualifier("userValidator")
@Component
public class UserRegistrationValidator implements Validator<UserRegisterRequestDTO> {


    @Override
    public void validate(UserRegisterRequestDTO userRegisterRequestDTO) throws BaseException {

        if (Objects.isNull(userRegisterRequestDTO.userName())) {
            throw new UserServiceOperationException.UserRegistrationException("The username can not be empty or null!");
        }

        if (CustomerIdNumberValidator.checkCustomerIdNumberLetter(userRegisterRequestDTO.userName())) {
            throw new UserServiceOperationException.UserRegistrationException("The username characters must be letter!");
        }
        if (Objects.isNull(userRegisterRequestDTO.password())) {
            throw new UserServiceOperationException.UserRegistrationException("The password can not be empty or null!");
        }
        if (Objects.isNull(userRegisterRequestDTO.birthday())) {
            throw new UserServiceOperationException.UserRegistrationException("The user birthday can not be empty or null!");
        }
        if (!userRegisterRequestDTO.birthday().before(new Date())) {
            throw new UserServiceOperationException.UserRegistrationException("The user birthday can not be before today.");
        }
        if (Objects.isNull(userRegisterRequestDTO.email())) {
            throw new UserServiceOperationException.UserRegistrationException("The email can not be empty or null!");
        }

        if(!checkEmail(userRegisterRequestDTO.email()))
        {
            throw new UserServiceOperationException.UserRegistrationException("The email format ınvalid!");
        }

        if (Objects.isNull(userRegisterRequestDTO.callNumber())) {
            throw new UserServiceOperationException.UserRegistrationException("The call number can not be empty or null!");
        }
        if (Objects.isNull(userRegisterRequestDTO.userType())) {
            throw new UserServiceOperationException.UserRegistrationException("The user type can not be empty or null!");
        }

    }






}
