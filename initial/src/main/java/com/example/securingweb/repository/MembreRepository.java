package com.example.securingweb.repository;

import com.example.securingweb.model.AdherentEntity;
import com.example.securingweb.model.MembreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MembreRepository extends JpaRepository<MembreEntity, UUID> {
    Optional<MembreEntity> findByEmail(String email);

}
