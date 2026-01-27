package com.example.securingweb.repository;

import com.example.securingweb.model.AdresseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdresseRepository extends JpaRepository<AdresseEntity, UUID> {

}
