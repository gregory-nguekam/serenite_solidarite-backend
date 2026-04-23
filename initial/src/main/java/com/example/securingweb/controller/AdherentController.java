package com.example.securingweb.controller;

import com.example.securingweb.dto.AuthResponse;
import com.example.securingweb.dto.LoginRequest;
import com.example.securingweb.dto.LoginResponse;
import com.example.securingweb.dto.RegisterAdherentRequest;
import com.example.securingweb.model.AdherentEntity;
import com.example.securingweb.repository.AdherentRepository;
import com.example.securingweb.security.JwtService;
import com.example.securingweb.service.AdherentService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/adherent")
public class AdherentController {

    private final AdherentService adherentService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AdherentRepository adherentRepository;

    public AdherentController(AdherentService adherentService, AdherentRepository adherentRepository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.adherentService = adherentService;
        this.adherentRepository = adherentRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.email, req.password)
        );

        var adherent = adherentRepository.findByEmail(req.email).orElseThrow();
        String token = jwtService.generateToken(adherent.getEmail(), adherent.getRole().name());
        return new LoginResponse(token);
    }

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AuthResponse registerAdherent(
            @ModelAttribute @Valid RegisterAdherentRequest req
    ) {
        String token = adherentService.registerAdherent(req);
        return new AuthResponse(token);
    }


}
