package org.example.aleasofttrainner;

import org.example.aleasofttrainner.Security.AppUser;
import org.example.aleasofttrainner.Security.AppUserRepository;
import org.example.aleasofttrainner.Security.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class LoadData {

    @Bean
    CommandLineRunner seedData(
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            if (appUserRepository.count() == 0) {
                appUserRepository.save(AppUser.builder()
                        .email("admin@hotel.local")
                        .password(passwordEncoder.encode("Admin@123"))
                        .role(Role.ADMIN)
                        .build());

                appUserRepository.save(AppUser.builder()
                        .email("guest@hotel.local")
                        .password(passwordEncoder.encode("Guest@123"))
                        .role(Role.GUEST)
                        .build());

            }
        };
    }
}