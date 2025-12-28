package com.example.quizapp.service;

import com.example.quizapp.entity.User;
import org.springframework.security.core.Authentication;

import java.util.Set;

public interface TokenService {
    String generateToken(User user, Set<String> roles);

    Authentication getAuthenticationFromToken(String token);
}
