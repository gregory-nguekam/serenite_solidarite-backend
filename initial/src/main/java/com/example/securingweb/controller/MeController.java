package com.example.securingweb.controller;

import com.example.securingweb.repository.AdherentRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MeController {

    private final AdherentRepository adherentRepository;

    public MeController(AdherentRepository adherentRepository) {
        this.adherentRepository = adherentRepository;
    }

    @GetMapping("/me")
    public Object me(Authentication authentication) {
        String email = authentication.getName();
        var a = adherentRepository.findByEmail(email).orElseThrow();
        var membreNom = a.getMembres().stream().findFirst().map(m -> m.getNom()).orElse(null);

        return new Object() {
            public final String adherentId = a.getId().toString();
            public final String nom = a.getNom();
            public final String prenom = a.getPrenom();
            public final String email = a.getEmail();
            public final String role = a.getRole().name();
            //public final String membreNom = a.getMembres();
        };
    }
}
