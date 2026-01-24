package com.example.securingweb.api;

import com.example.securingweb.repo.AdherentRepository;
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

        return new Object() {
            public final String adherentId = a.getId().toString();
            public final String nom = a.getNom();
            public final String prenom = a.getPrenom();
            public final String email = a.getEmail();
            public final String role = a.getRole().name();
            public final String membreNom = a.getMembre().getNom();
            public final String membreType = a.getMembre().getTypeMembre().name();
        };
    }
}
