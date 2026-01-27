package com.example.securingweb.service;

import com.example.securingweb.dto.RegisterAdherentRequest;
import com.example.securingweb.model.AdherentEntity;
import com.example.securingweb.model.AdresseEntity;
import com.example.securingweb.model.AppRole;
import com.example.securingweb.model.DocumentEntity;
import com.example.securingweb.model.DocumentType;
import com.example.securingweb.repository.AdherentRepository;
import com.example.securingweb.repository.AdresseRepository;
import com.example.securingweb.repository.DocumentRepository;
import com.example.securingweb.repository.MembreRepository;
import com.example.securingweb.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AdherentService {

    private final AdherentRepository adherentRepository;
    private final MembreRepository membreRepository;
    private final AdresseRepository adresseRepository;
    private final DocumentRepository documentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AdherentService(
            AdherentRepository adherentRepository,
            MembreRepository membreRepository,
            AdresseRepository adresseRepository,
            DocumentRepository documentRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.adherentRepository = adherentRepository;
        this.membreRepository = membreRepository;
        this.adresseRepository = adresseRepository;
        this.documentRepository = documentRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional
    public String registerAdherent(RegisterAdherentRequest req, MultipartFile identite, MultipartFile rib,
            MultipartFile justificatifDomicile) {
        if (adherentRepository.findByEmail(req.email).isPresent()) {
            throw new IllegalArgumentException("EMAIL_ALREADY_USED : Email existe deja");
        }

        var adherent = new AdherentEntity();
        adherent.setNom(req.nom);
        adherent.setPrenom(req.prenom);
        adherent.setEmail(req.email);
        adherent.setTelephone(req.telephone);
        adherent.setPassword(passwordEncoder.encode("toto"));
        adherent.setRole(AppRole.ADHERENT);
        adherent.setIsActive(true);

        if (req.membreId != null) {
            membreRepository.findById(req.membreId).ifPresent(m -> adherent.getMembres().add(m));
        } else {
            membreRepository.findByEmail("contact@direct.com").ifPresent(m -> adherent.getMembres().add(m));
        }

        adherentRepository.save(adherent);

        var adresse = new AdresseEntity();
        adresse.setNumeroRue(req.adresse.numeroRue);
        adresse.setRue(req.adresse.rue);
        adresse.setCodePostal(req.adresse.codePostal);
        adresse.setVille(req.adresse.ville);
        adresse.setComplement(req.adresse.complement);
        adresse.setAdherent(adherent);
        adresseRepository.save(adresse);

        saveDocument(adherent, identite, DocumentType.IDENTITE);
        saveDocument(adherent, rib, DocumentType.RIB);
        saveDocument(adherent, justificatifDomicile, DocumentType.JUSTIFICATIF_DOMICILE);

        return jwtService.generateToken(adherent.getEmail(), adherent.getRole().name());
    }

    private void saveDocument(AdherentEntity adherent, MultipartFile fichier, DocumentType type) {
        if (fichier == null || fichier.isEmpty()) {
            throw new IllegalArgumentException("Fichier manquant: " + type);
        }

        try {
            var doc = new DocumentEntity();
            doc.setNom(fichier.getOriginalFilename());
            doc.setType(type);
            doc.setFichier(fichier.getBytes());
            doc.setAdherent(adherent);
            documentRepository.save(doc);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Erreur lecture fichier: " + type, ex);
        }
    }
}
