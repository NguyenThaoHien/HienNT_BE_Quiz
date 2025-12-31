package com.example.quizapp.controller;

import com.example.quizapp.dto.request.LoginRequest;
import com.example.quizapp.dto.request.RegisterRequest;
import com.example.quizapp.dto.response.ApiResponse;
import com.example.quizapp.dto.response.LoginResponse;
import com.example.quizapp.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "User Login", description = "Authenticates a user with username and password, returns JWT token upon successful authentication")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Login successful", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Invalid credentials", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Validation failed", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
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
    @Operation(summary = "User Registration", description = "Creates a new user account and returns JWT token upon successful registration")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Registration successful", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Username or email already exists", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Validation failed", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    public ResponseEntity<ApiResponse<LoginResponse>> register(
            @Valid @RequestBody RegisterRequest registerRequest) {

        LoginResponse result = authService.register(registerRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.created(result, "Registration successful"));
    }
}
