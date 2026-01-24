package com.example.securingweb;

import com.example.securingweb.domain.MembreType;
import com.example.securingweb.dto.RegisterAdherentRequest;
import com.example.securingweb.domain.AdherentEntity;
import com.example.securingweb.domain.AppRole;
import com.example.securingweb.repo.AdherentRepository;
import com.example.securingweb.repo.MembreRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class RegistrationService {

    private final AdherentRepository adherentRepository;
    private final MembreRepository membreRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public RegistrationService(
            AdherentRepository adherentRepository,
            MembreRepository membreRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.adherentRepository = adherentRepository;
        this.membreRepository = membreRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional
    public String registerAdherent(RegisterAdherentRequest req) {
        if (adherentRepository.findByEmail(req.email).isPresent()) {
            throw new IllegalArgumentException("EMAIL_ALREADY_USED " + " : Email existe déjà");
        }

        // membre par défaut (obligatoire car membre_id NOT NULL)
        var membre = membreRepository.findFirstByTypeMembre(MembreType.DIRECT)
                .orElseThrow(() -> new IllegalStateException("DEFAULT_MEMBRE_DIRECT_NOT_FOUND"));

        var a = new AdherentEntity();
        a.setMembre(membre);
        a.setNom(req.nom);
        a.setPrenom(req.prenom);
        a.setEmail(req.email);

        // mot de passe par défaut
        a.setPasswordHash(passwordEncoder.encode("toto"));
        a.setRole(AppRole.ADHERENT);

        adherentRepository.save(a);

        // pour démo : token direct
        return jwtService.generateToken(a.getEmail(), a.getRole().name());
    }
}
