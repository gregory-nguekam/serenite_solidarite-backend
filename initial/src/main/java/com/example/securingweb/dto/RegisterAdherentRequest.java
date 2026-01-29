package com.example.securingweb.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class RegisterAdherentRequest {

    private UUID membreId;

    @NotBlank private String nom;
    @NotBlank private String prenom;

    @Email @NotBlank
    private String email;

    @NotBlank private String telephone;

    @Valid @NotNull
    private AdresseDto adresse;

    @NotNull
    private MultipartFile identite;

    @NotNull
    private MultipartFile rib;

    @NotNull
    private MultipartFile justificatifDomicile;

    public UUID getMembreId() {
        return membreId;
    }

    public void setMembreId(UUID membreId) {
        this.membreId = membreId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public AdresseDto getAdresse() {
        return adresse;
    }

    public void setAdresse(AdresseDto adresse) {
        this.adresse = adresse;
    }

    public MultipartFile getIdentite() {
        return identite;
    }

    public void setIdentite(MultipartFile identite) {
        this.identite = identite;
    }

    public MultipartFile getRib() {
        return rib;
    }

    public void setRib(MultipartFile rib) {
        this.rib = rib;
    }

    public MultipartFile getJustificatifDomicile() {
        return justificatifDomicile;
    }

    public void setJustificatifDomicile(MultipartFile justificatifDomicile) {
        this.justificatifDomicile = justificatifDomicile;
    }
}
