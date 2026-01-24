package com.example.securingweb;

import com.example.securingweb.repo.AdherentRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdherentUserDetailsService implements UserDetailsService {

    private final AdherentRepository adherentRepository;

    public AdherentUserDetailsService(AdherentRepository adherentRepository) {
        this.adherentRepository = adherentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = adherentRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Adhérent not found"));

        // Spring recommande le préfixe ROLE_
        var authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().name());

        return new User(user.getEmail(), user.getPasswordHash(), user.getActif(), true, true, true, List.of(authority));
    }
}
