package com.example.securingweb.dto;

import com.example.securingweb.model.enums.MembreType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class RegisterMembreRequest {

    @NotNull
    private MembreType type;

    @NotBlank
    private String nom;

    private String initiales;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String telephone;

    private String centreInteret;

    @NotBlank
    private String deleguePrincipal;

    private String delegueAdjoint1;
    private String delegueAdjoint2;
    private String delegueAdjoint3;

    @Valid
    @NotNull
    private AdresseDto adresse;

    @NotNull
    private MultipartFile siret;

    @NotNull
    private MultipartFile listeAdherents;

    public MembreType getType() {
        return type;
    }

    public void setType(MembreType type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getInitiales() {
        return initiales;
    }

    public void setInitiales(String initiales) {
        this.initiales = initiales;
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

    public String getCentreInteret() {
        return centreInteret;
    }

    public void setCentreInteret(String centreInteret) {
        this.centreInteret = centreInteret;
    }

    public String getDeleguePrincipal() {
        return deleguePrincipal;
    }

    public void setDeleguePrincipal(String deleguePrincipal) {
        this.deleguePrincipal = deleguePrincipal;
    }

    public String getDelegueAdjoint1() {
        return delegueAdjoint1;
    }

    public void setDelegueAdjoint1(String delegueAdjoint1) {
        this.delegueAdjoint1 = delegueAdjoint1;
    }

    public String getDelegueAdjoint2() {
        return delegueAdjoint2;
    }

    public void setDelegueAdjoint2(String delegueAdjoint2) {
        this.delegueAdjoint2 = delegueAdjoint2;
    }

    public String getDelegueAdjoint3() {
        return delegueAdjoint3;
    }

    public void setDelegueAdjoint3(String delegueAdjoint3) {
        this.delegueAdjoint3 = delegueAdjoint3;
    }

    public AdresseDto getAdresse() {
        return adresse;
    }

    public void setAdresse(AdresseDto adresse) {
        this.adresse = adresse;
    }

    public MultipartFile getSiret() {
        return siret;
    }

    public void setSiret(MultipartFile siret) {
        this.siret = siret;
    }

    public MultipartFile getListeAdherents() {
        return listeAdherents;
    }

    public void setListeAdherents(MultipartFile listeAdherents) {
        this.listeAdherents = listeAdherents;
    }
}
