package com.example.securingweb.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_adherent",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_adherent_email", columnNames = "email"),
                @UniqueConstraint(name = "uq_adherent_login", columnNames = "login")
        }
)
public class AdherentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "membre_id", nullable = false)
    @Enumerated(EnumType.STRING)
    private MembreEntity membre;

    @Column(nullable = false, length = 120)
    private String nom;

    @Column(nullable = false, length = 120)
    private String prenom;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(length = 80)
    private String login;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppRole role = AppRole.ADHERENT;

    @Column(name = "date_inscription", nullable = false)
    private LocalDate dateInscription;

    @Column(name = "carence_fin")
    private LocalDate carenceFin;

    @Column(nullable = false)
    private Boolean actif = true;

    @Column(name = "rgpd_consent", nullable = false)
    private Boolean rgpdConsent = false;

    @Column(name = "rgpd_consent_at")
    private OffsetDateTime rgpdConsentAt;



    // getters/setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public MembreEntity getMembre() {
        return membre;
    }

    public void setMembre(MembreEntity membre) {
        this.membre = membre;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public AppRole getRole() {
        return role;
    }

    public void setRole(AppRole role) {
        this.role = role;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    public LocalDate getCarenceFin() {
        return carenceFin;
    }

    public void setCarenceFin(LocalDate carenceFin) {
        this.carenceFin = carenceFin;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public Boolean getRgpdConsent() {
        return rgpdConsent;
    }

    public void setRgpdConsent(Boolean rgpdConsent) {
        this.rgpdConsent = rgpdConsent;
    }

    public OffsetDateTime getRgpdConsentAt() {
        return rgpdConsentAt;
    }

    public void setRgpdConsentAt(OffsetDateTime rgpdConsentAt) {
        this.rgpdConsentAt = rgpdConsentAt;
    }
}

