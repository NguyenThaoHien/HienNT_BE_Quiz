package com.example.quizapp.controller;

import com.example.quizapp.dto.request.LoginRequest;
import com.example.quizapp.dto.request.RegisterRequest;
import com.example.quizapp.dto.response.ApiResponse;
import com.example.quizapp.dto.response.LoginResponse;
import com.example.quizapp.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    /**
     * Authenticates a user and returns a JWT token.
     *
     * @param loginRequest the login credentials
     * @return ResponseEntity containing the authentication response with JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
          @Valid @RequestBody LoginRequest loginRequest
    ){
        LoginResponse result = authService.login(loginRequest);

        return ResponseEntity.ok(ApiResponse.success(result, "Login successfully"));
    }

    /**
     * Registers a new user account.
     *
     * @param registerRequest the registration details
     * @return ResponseEntity containing the authentication response with JWT token
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<LoginResponse>> register(
            @Valid @RequestBody RegisterRequest registerRequest) {

        LoginResponse result = authService.register(registerRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.created(result, "Registration successful"));
    }
}
