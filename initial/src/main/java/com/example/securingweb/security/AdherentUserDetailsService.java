package com.example.securingweb.security;

import com.example.securingweb.repository.AdherentRepository;
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

        return new User(user.getEmail(), user.getPassword(), user.getIsActive(), true, true, true, List.of(authority));
    }
}
