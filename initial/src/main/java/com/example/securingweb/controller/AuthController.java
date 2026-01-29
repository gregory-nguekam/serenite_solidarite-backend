package com.example.securingweb.controller;

import com.example.securingweb.dto.AuthResponse;
import com.example.securingweb.dto.LoginRequest;
import com.example.securingweb.dto.LoginResponse;
import com.example.securingweb.repository.AdherentRepository;
import com.example.securingweb.security.JwtService;
import com.example.securingweb.service.AdherentService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AdherentService adherentService;
    private final AdherentRepository adherentRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, AdherentRepository adherentRepository, AdherentService adherentService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.adherentRepository = adherentRepository;
        this.adherentService = adherentService;
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

}
