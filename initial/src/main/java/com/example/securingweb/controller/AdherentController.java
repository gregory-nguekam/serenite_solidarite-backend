package com.example.securingweb.controller;

import com.example.securingweb.dto.AuthResponse;
import com.example.securingweb.dto.RegisterAdherentRequest;
import com.example.securingweb.service.AdherentService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/adherent")
public class AdherentController {

    private final AdherentService adherentService;

    public AdherentController(AdherentService adherentService) {
        this.adherentService = adherentService;
    }

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AuthResponse registerAdherent(
            @RequestPart("data") @Valid RegisterAdherentRequest req,
            @RequestPart("identite") MultipartFile identite,
            @RequestPart("rib") MultipartFile rib,
            @RequestPart("justificatifDomicile") MultipartFile justificatifDomicile
    ) {
        String token = adherentService.registerAdherent(req, identite, rib, justificatifDomicile);
        return new AuthResponse(token);
    }

    // @PostMapping("/delete")
    // public AuthResponse deleteAdherent(@RequestBody @Valid
    // RegisterAdherentRequest req) {
    // String token = adherentService.registerAdherent(req);
    // return new AuthResponse(token);
    // }

    // @PostMapping("/update")
    // public AuthResponse updateAdherent(@RequestBody @Valid
    // RegisterAdherentRequest req) {
    // String token = adherentService.registerAdherent(req);
    // return new AuthResponse(token);
    // }

    // @PostMapping("/get")
    // public AuthResponse getAdherent(@RequestBody @Valid RegisterAdherentRequest
    // req) {
    // String token = adherentService.registerAdherent(req);
    // return new AuthResponse(token);
    // }
}
