package com.example.securingweb.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class RegisterAdherentRequest {

    public UUID membreId;

    @NotBlank public String nom;
    @NotBlank public String prenom;

    @Email @NotBlank
    public String email;

    public String telephone;
}
