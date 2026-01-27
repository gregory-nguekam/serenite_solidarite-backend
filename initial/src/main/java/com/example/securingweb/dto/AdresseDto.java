package com.example.securingweb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AdresseDto {

    @NotBlank public String numeroRue;
    @NotBlank public String rue;
    @NotNull public Integer codePostal;
    @NotBlank public String ville;
    public String complement;

}
