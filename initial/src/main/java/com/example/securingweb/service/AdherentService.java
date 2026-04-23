package com.example.securingweb.service;

import com.example.securingweb.dto.RegisterAdherentRequest;
import com.example.securingweb.model.AdherentEntity;
import com.example.securingweb.model.enums.AppRole;
import com.example.securingweb.model.DocumentEntity;
import com.example.securingweb.model.enums.DocumentType;
import com.example.securingweb.repository.AdherentRepository;
import com.example.securingweb.repository.DocumentRepository;
import com.example.securingweb.repository.MembreRepository;
import com.example.securingweb.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AdherentService {

    private final AdherentRepository adherentRepository;
    private final MembreRepository membreRepository;
    private final DocumentRepository documentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;

    public AdherentService(
            AdherentRepository adherentRepository,
            MembreRepository membreRepository,
            DocumentRepository documentRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            EmailService emailService) {
        this.adherentRepository = adherentRepository;
        this.membreRepository = membreRepository;
        this.documentRepository = documentRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.emailService = emailService;
    }

    @Transactional
    public String registerAdherent(RegisterAdherentRequest req) {
        if (adherentRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new IllegalArgumentException("EMAIL_ALREADY_USED : Email existe deja");
        }

        AdherentEntity adherent = new AdherentEntity();
        adherent.setNom(req.getNom());
        adherent.setPrenom(req.getPrenom());
        adherent.setEmail(req.getEmail());
        adherent.setTelephone(req.getTelephone());
        adherent.setPassword(passwordEncoder.encode(req.getPassword()));
        adherent.setRole(AppRole.ADHERENT);
        adherent.setIsActive(true);
        adherent.setIsValidated(false);

        if (req.getMembreId() != null) {
            membreRepository.findById(req.getMembreId()).ifPresent(m -> adherent.getMembres().add(m));
        } else {
            membreRepository.findByEmail("contact@direct.com").ifPresent(m -> adherent.getMembres().add(m));
        }

        adherentRepository.save(adherent);

        var adresseDto = req.getAdresse();
        var adresse = new AdresseEntity();
        adresse.setNumeroRue(adresseDto.getNumeroRue());
        adresse.setRue(adresseDto.getRue());
        adresse.setCodePostal(adresseDto.getCodePostal());
        adresse.setVille(adresseDto.getVille());
        adresse.setComplement(adresseDto.getComplement());
        adresse.setAdherent(adherent);
        adresseRepository.save(adresse);

        saveDocument(adherent, req.getIdentite(), DocumentType.IDENTITE);
        saveDocument(adherent, req.getRib(), DocumentType.RIB);
        saveDocument(adherent, req.getJustificatifDomicile(), DocumentType.JUSTIFICATIF_DOMICILE);

        sendValidationEmailAfterCommit(adherent);

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

    private void sendValidationEmailAfterCommit(AdherentEntity adherent) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    emailService.sendEmailValidation(adherent);
                }
            });
        } else {
            emailService.sendEmailValidation(adherent);
        }
    }
}
