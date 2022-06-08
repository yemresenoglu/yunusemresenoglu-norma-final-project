package com.example.finalproject.controller;

import com.example.finalproject.dto.UserLoginRequestDTO;
import com.example.finalproject.dto.UserRegisterRequestDTO;
import com.example.finalproject.security.JwtTokenGenerator;
import com.example.finalproject.service.UserService;
import com.example.finalproject.validator.Validator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@AllArgsConstructor
public class HomeController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;
    private final UserService userService;
    private final Validator<UserRegisterRequestDTO> userRegistrationValidator;


    //user login
    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userLoginRequestDTO.userName(), userLoginRequestDTO.password());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenGenerator.generateJwtToken(auth);
        return jwtToken;
    }

    //user registration
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        userRegistrationValidator.validate(userRegisterRequestDTO);
        userService.createUser(userRegisterRequestDTO);
        return ResponseEntity.ok().build();
    }
}
