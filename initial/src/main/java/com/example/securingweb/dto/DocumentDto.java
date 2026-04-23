package com.example.securingweb.dto;

import com.example.securingweb.model.enums.DocumentType;

public class DocumentDto {
    public String id;
    public String nom;
    public DocumentType type;
    public Long size;
    public String fichierBase64;
}
