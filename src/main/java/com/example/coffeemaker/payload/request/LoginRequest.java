package com.example.coffeemaker.payload.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {

    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @Size(min = 6,message = "Password must contain at least 6 characters")
    @NotEmpty(message = "Password cannot be empty")
    private String password;

}
