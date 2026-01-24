package com.example.securingweb;

import com.example.securingweb.domain.*;
import com.example.securingweb.repo.AdherentRepository;
import com.example.securingweb.repo.MembreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SeedData {

    @Bean
    CommandLineRunner seedRunner(SeedService seedService) {
        // important : on appelle un bean Spring (proxy) -> @Transactional fonctionne
        return args -> seedService.seed();
    }

    @Component
    public static class SeedService {
        private final AdherentRepository adherentRepository;
        private final MembreRepository membreRepository;
        private final PasswordEncoder encoder;

        public SeedService(AdherentRepository adherentRepository, MembreRepository membreRepository, PasswordEncoder encoder) {
            this.adherentRepository = adherentRepository;
            this.membreRepository = membreRepository;
            this.encoder = encoder;
        }

        @Transactional
        public void seed() {
            if (adherentRepository.findByEmail("greg@example.com").isPresent()) return;

            var membre = new MembreEntity();
            membre.setTypeMembre(MembreType.ASSOCIATION);
            membre.setNom("Bamboutos");
            membre.setInitiales("BFC");
            membre = membreRepository.save(membre); // ✅ transaction OK

            var a = new AdherentEntity();
            a.setMembre(membre);
            a.setNom("Nguekam");
            a.setPrenom("Gregory");
            a.setEmail("greg@example.com");
            a.setPasswordHash(encoder.encode("Password123!"));
            a.setRole(AppRole.ADHERENT);

            adherentRepository.save(a);
        }
    }
}
