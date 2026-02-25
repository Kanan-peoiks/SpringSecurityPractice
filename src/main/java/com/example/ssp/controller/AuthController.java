package com.example.ssp.controller;

import com.example.ssp.jwt.JwtService;
import com.example.ssp.model.User;
import com.example.ssp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");

        userRepository.save(user);

        return "User registered!";
    }

    @PostMapping("/login")
    public String login(@RequestBody User request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtService.generateToken(user.getUsername());
    }

    @GetMapping("/hello")
    public String hello() {
        return "JWT works successfully!";
    }
}