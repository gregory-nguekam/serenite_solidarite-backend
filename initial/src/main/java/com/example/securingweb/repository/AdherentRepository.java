package com.example.securingweb.repository;

import com.example.securingweb.model.AdherentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;
import java.util.UUID;


public interface AdherentRepository extends JpaRepository<AdherentEntity, UUID>, JpaSpecificationExecutor<AdherentEntity> {
    Optional<AdherentEntity> findByEmail(String email);
    Optional<AdherentEntity> findByPrenom(String prenom);
    Optional<AdherentEntity> findByNom(String nom);
    Optional<AdherentEntity> findByRole(String role);
}
