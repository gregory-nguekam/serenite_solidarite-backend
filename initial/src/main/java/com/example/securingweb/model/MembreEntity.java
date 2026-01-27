package com.example.securingweb.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "membre")
public class MembreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 50)
    private String initiales;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 20)
    private String telephone;

    @Column(name = "centre_interet", length = 100)
    private String centreInteret;

    @Column(name = "delegue_principal", nullable = false, length = 50)
    private String deleguePrincipal;

    @Column(name = "delegue_adjoint1", length = 50)
    private String delegueAdjoint1;

    @Column(name = "delegue_adjoint2", length = 50)
    private String delegueAdjoint2;

    @Column(name = "delegue_adjoint3", length = 50)
    private String delegueAdjoint3;

    @ManyToMany(mappedBy = "membres")
    private Set<AdherentEntity> adherents = new HashSet<>();


    // getters/setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Set<AdherentEntity> getAdherents() {
        return adherents;
    }

    public void setAdherents(Set<AdherentEntity> adherents) {
        this.adherents = adherents;
    }
}
