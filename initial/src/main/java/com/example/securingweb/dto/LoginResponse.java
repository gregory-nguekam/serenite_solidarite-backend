package com.example.securingweb.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginResponse {
    public String token;

    public LoginResponse(String token) {
        this.token = token;
    }
}
