package com.example.coffeemaker.payload.request;

import com.example.coffeemaker.anatations.UniqueUsername;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class RegistrationRequest {


    @NotEmpty(message = "Username cannot be empty")
    private String CoffeeHouseName;
    @NotEmpty(message = "Username cannot be empty")
    @UniqueUsername
    private String username;
    @Size(min = 6,message = "Password must contain at least 6 characters")
    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
