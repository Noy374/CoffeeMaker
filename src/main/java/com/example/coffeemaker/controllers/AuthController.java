package com.example.coffeemaker.controllers;


import com.example.coffeemaker.payload.request.LoginRequest;
import com.example.coffeemaker.payload.request.RegistrationRequest;
import com.example.coffeemaker.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@AllArgsConstructor
@RequestMapping("/coffee_house")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/reg")
    ResponseEntity<Object> registration (@Valid @RequestBody RegistrationRequest registrationRequest, BindingResult bindingResult){
        return authService.registration(registrationRequest,bindingResult);
    }

    @PostMapping("/login")
    ResponseEntity<Object> logIn(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        return authService.login(loginRequest,bindingResult);
    }


}
