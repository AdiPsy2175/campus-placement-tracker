package com.aditya.campusplacementtracker.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.aditya.campusplacementtracker.entity.User;
import com.aditya.campusplacementtracker.enums.Role;
import com.aditya.campusplacementtracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.findByUsername("admin").isEmpty()) {

            User admin = new User();

            admin.setUsername("admin");

            admin.setPassword(passwordEncoder.encode("admin123"));

            admin.setRole(Role.ADMIN);

            userRepository.save(admin);

            System.out.println("Admin user created successfully.");

        }

    }

}