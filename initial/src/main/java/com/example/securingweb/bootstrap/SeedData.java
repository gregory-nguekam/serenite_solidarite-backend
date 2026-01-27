package com.example.securingweb.bootstrap;

import com.example.securingweb.model.AdherentEntity;
import com.example.securingweb.model.AdresseEntity;
import com.example.securingweb.model.AppRole;
import com.example.securingweb.model.MembreEntity;
import com.example.securingweb.repository.AdherentRepository;
import com.example.securingweb.repository.MembreRepository;
import com.example.securingweb.repository.AdresseRepository;
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
        private final AdresseRepository adresseRepository;
        private final PasswordEncoder encoder;

        public SeedService(AdherentRepository adherentRepository, MembreRepository membreRepository, AdresseRepository adresseRepository,
                           PasswordEncoder encoder) {
            this.adherentRepository = adherentRepository;
            this.membreRepository = membreRepository;
            this.adresseRepository = adresseRepository;
            this.encoder = encoder;
        }

        @Transactional
        public void seed() {
            if (adherentRepository.findByEmail("greg@example.com").isPresent())
                return;

            var membre = new MembreEntity();
            membre.setNom("Direct");
            membre.setInitiales("DIRECT");
            membre.setEmail("contact@direct.com");
            membre.setTelephone("0600000000");
            membre.setCentreInteret("Solidarité");
            membre.setDeleguePrincipal("Gregory N.");
            membre.setDelegueAdjoint1("Adjoint 1");
            membre.setDelegueAdjoint2("Adjoint 2");
            membre.setDelegueAdjoint3("Adjoint 3");

            var adresse = new AdresseEntity();
            adresse.setNumeroRue("2bis");
            adresse.setRue("rue de la Bergere");
            adresse.setCodePostal(85200);
            adresse.setVille("Reuil-la-Fitte");
            adresse.setMembre(membre);
            adresse = adresseRepository.save(adresse);

            membre = membreRepository.save(membre);

            var a = new AdherentEntity();
            a.setNom("Nguekam");
            a.setPrenom("Gregory");
            a.setEmail("greg@example.com");
            a.setTelephone("0600000001");
            a.setPassword(encoder.encode("toto"));
            a.setRole(AppRole.ADHERENT);
            a.setIsActive(true);
            a.getMembres().add(membre);
            var adresse2 = new AdresseEntity();
            adresse2.setNumeroRue("30");
            adresse2.setRue("rue de la Roue");
            adresse2.setCodePostal(85800);
            adresse2.setVille("Reuil-la-True");

            adresse2.setAdherent(a);
            adresse2 = adresseRepository.save(adresse2);

            adherentRepository.save(a);
        }
    }
}
