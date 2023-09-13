package com.example.coffeemaker.services;

import com.example.coffeemaker.payload.request.LoginRequest;
import com.example.coffeemaker.payload.request.RegistrationRequest;
import com.example.coffeemaker.payload.response.MessageResponse;
import com.example.coffeemaker.payload.response.loginResponse;
import com.example.coffeemaker.security.JwtTokenUtils;
import com.example.coffeemaker.validations.ResponseErrorValidation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
@AllArgsConstructor
public class AuthService {
    private final ResponseErrorValidation responseErrorValidation;
    private final AuthenticationManager authenticationManager;

    private final CoffeeHouseService coffeeHouseService;

    private final JwtTokenUtils jwtTokenUtils;

    public ResponseEntity<Object> registration (@Valid @RequestBody RegistrationRequest registrationRequest, BindingResult bindingResult){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        coffeeHouseService.saveCoffeeHouse(registrationRequest);
        return ResponseEntity.ok(new MessageResponse("You have successfully completed registration"));
    }
    public  ResponseEntity<Object> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        try {
            authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));


        }catch (Exception i) {
            ResponseEntity.badRequest().body(new MessageResponse("Incorrect username or password"));
        }
        UserDetails userDetails= coffeeHouseService.loadUserByUsername(loginRequest.getUsername());

        String token= jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new loginResponse(token));

    }


}
