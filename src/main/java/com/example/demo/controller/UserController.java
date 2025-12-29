package com.example.demo.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        return Map.of(
            "message", "Login successful",
            "username", request.username(),
            "authenticated", true,
            "role", authentication.getAuthorities().toString()
        );
    }

    @GetMapping("/logout")
    public Map<String, String> logout() {
        SecurityContextHolder.clearContext();
        return Map.of("message", "Logout successful");
    }

    @GetMapping("/me")
    public Map<String, Object> getCurrentUser(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return Map.of(
                "username", authentication.getName(),
                "authorities", authentication.getAuthorities().toString(),
                "authenticated", true
            );
        }
        return Map.of("authenticated", false);
    }

    record LoginRequest(String username, String password) {}
}