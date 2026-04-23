package com.example.securingweb.model;

import com.example.securingweb.model.enums.DocumentType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "document")
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, length = 500)
    private String nom;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private DocumentType type;

    @Lob
    @Column(nullable = false)
    private byte[] fichier;

    @Column(nullable = false)
    private UUID entiteId;

    @Column(nullable = false)
    private boolean isAdherent;

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

    public DocumentType getType() {
        return type;
    }

    public void setType(DocumentType type) {
        this.type = type;
    }

    public byte[] getFichier() {
        return fichier;
    }

    public void setFichier(byte[] fichier) {
        this.fichier = fichier;
    }

    public boolean isAdherent() {
        return isAdherent;
    }

    public void setAdherent(boolean adherent) {
        isAdherent = adherent;
    }

    public UUID getEntiteId() {
        return entiteId;
    }

    public void setEntiteId(UUID entiteId) {
        this.entiteId = entiteId;
    }
}
