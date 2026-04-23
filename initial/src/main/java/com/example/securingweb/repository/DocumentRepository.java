package com.example.securingweb.repository;

import com.example.securingweb.model.DocumentEntity;
import com.example.securingweb.model.enums.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DocumentRepository extends JpaRepository<DocumentEntity, UUID> {
    List<DocumentEntity> findByAdherentIdOrderByTypeAsc(UUID adherentId);
    Optional<DocumentEntity> findByAdherentIdAndType(UUID adherentId, DocumentType type);
    List<DocumentEntity> findByMembreIdOrderByTypeAsc(UUID membreId);
    Optional<DocumentEntity> findByMembreIdAndType(UUID membreId, DocumentType type);
}
