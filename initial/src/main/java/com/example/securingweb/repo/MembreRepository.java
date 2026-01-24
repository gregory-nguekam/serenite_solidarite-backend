package com.example.securingweb.repo;

import com.example.securingweb.domain.MembreEntity;
import com.example.securingweb.domain.MembreType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MembreRepository extends JpaRepository<MembreEntity, UUID> {
    Optional<MembreEntity> findFirstByTypeMembre(MembreType typeMembre);
}
