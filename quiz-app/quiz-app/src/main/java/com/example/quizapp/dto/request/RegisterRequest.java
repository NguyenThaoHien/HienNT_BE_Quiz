package com.example.quizapp.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

public record RegisterRequest(
        @Email
        @NotBlank
        @Schema(description = "Email", requiredMode = Schema.RequiredMode.REQUIRED)
        String email,
        @NotBlank
        @Schema(description = "Full name", requiredMode = Schema.RequiredMode.REQUIRED)
        String fullName,
        @Size(min = 8)
        @Schema(description = "Password", requiredMode = Schema.RequiredMode.REQUIRED)
        String password,
        @NotBlank
        @Schema(description = "Confirm password", requiredMode = Schema.RequiredMode.REQUIRED)
        String confirmPassword
) {}



