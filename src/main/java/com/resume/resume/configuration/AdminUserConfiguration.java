package com.resume.resume.configuration;

import com.resume.resume.model.Role;
import com.resume.resume.model.User;
import com.resume.resume.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Configuration
public class AdminUserConfiguration {

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            Optional<User> user = userRepository.findByRole(Role.ADMIN);

            if (user.isEmpty()) {
                String username = System.getenv("ADMIN_USERNAME") ;
                String password = System.getenv("ADMIN_PASSWORD");

                username = (username == null) ? "admin@admin.com" : username;
                password = (password == null) ? "admin" : password;

                User adminUser = new User(
                        username,
                        new BCryptPasswordEncoder().encode(password),
                        Role.ADMIN
                );
                userRepository.save(adminUser);
            }
        };
    }
}
