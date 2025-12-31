package com.example.quizapp.service;

import com.example.quizapp.dto.request.LoginRequest;
import com.example.quizapp.dto.request.RegisterRequest;
import com.example.quizapp.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse register(RegisterRequest registerRequest);
}
