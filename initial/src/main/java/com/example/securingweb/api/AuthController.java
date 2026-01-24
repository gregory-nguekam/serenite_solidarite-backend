package com.example.securingweb.api;

import com.example.securingweb.dto.LoginRequest;
import com.example.securingweb.dto.LoginResponse;
import com.example.securingweb.repo.AdherentRepository;
import com.example.securingweb.JwtService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import com.example.securingweb.dto.RegisterAdherentRequest;
import com.example.securingweb.dto.AuthResponse;
import com.example.securingweb.RegistrationService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RegistrationService registrationService;
    private final AdherentRepository adherentRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, AdherentRepository adherentRepository, RegistrationService registrationService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.adherentRepository = adherentRepository;
        this.registrationService = registrationService;
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
    @PostMapping("/register-adherent")
    public AuthResponse registerAdherent(@RequestBody @Valid RegisterAdherentRequest req) {
        String token = registrationService.registerAdherent(req);
        return new AuthResponse(token);
    }

}
