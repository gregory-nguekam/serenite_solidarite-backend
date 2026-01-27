package com.example.securingweb.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "adresse")
public class AdresseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "numero_rue", nullable = false, length = 10)
    private String numeroRue;

    @Column(nullable = false, length = 100)
    private String rue;

    @Column(name = "code_postal", nullable = false)
    private Integer codePostal;

    @Column(nullable = false, length = 50)
    private String ville;

    @Column(length = 100)
    private String complement;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_adherent")
    private AdherentEntity adherent;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_membre")
    private MembreEntity membre;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumeroRue() {
        return numeroRue;
    }

    public void setNumeroRue(String numeroRue) {
        this.numeroRue = numeroRue;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public Integer getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(Integer codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public MembreEntity getMembre(){
        return membre;
    }

    public void setMembre(MembreEntity membre){
        this.membre = membre;
    }

    public AdherentEntity getAdherent(){
        return adherent;
    }

    public void setAdherent(AdherentEntity adherent){
        this.adherent = adherent;
    }
}
