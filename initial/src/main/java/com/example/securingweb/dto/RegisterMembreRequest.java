package com.example.securingweb.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterMembreRequest {

    // Adhérent
    @NotBlank public String nom;
    @NotBlank public String prenom;
    @Email @NotBlank public String email;
    @NotBlank public String password;

    // Membre (association/groupe/famille/unité)
    // valeurs attendues: ASSOCIATION, GROUPE, FAMILLE, UNITE, DIRECT
    @NotBlank public String typeMembre;
    @NotBlank public String membreNom;
    public String membreInitiales;

    // RGPD
    public boolean rgpdConsent;
}
