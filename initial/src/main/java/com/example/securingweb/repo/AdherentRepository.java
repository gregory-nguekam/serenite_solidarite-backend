package com.example.securingweb.repo;

import com.example.securingweb.domain.AdherentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;


public interface AdherentRepository extends JpaRepository<AdherentEntity, UUID> {
    Optional<AdherentEntity> findByEmail(String email);
}
