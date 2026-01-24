package com.example.securingweb.domain;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_membre")
public class MembreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_membre", nullable = false)
    private MembreType typeMembre;

    @Column(nullable = false, length = 200)
    private String nom;

    @Column(length = 20)
    private String initiales;

    @Column(length = 255)
    private String email;

    @Column(length = 30)
    private String telephone;

    @Column(name = "carence_jours", nullable = false)
    private Integer carenceJours = 180;


    // getters/setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public MembreType getTypeMembre() {
        return typeMembre;
    }

    public void setTypeMembre(MembreType typeMembre) {
        this.typeMembre = typeMembre;
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

    public Integer getCarenceJours() {
        return carenceJours;
    }

    public void setCarenceJours(Integer carenceJours) {
        this.carenceJours = carenceJours;
    }

}
