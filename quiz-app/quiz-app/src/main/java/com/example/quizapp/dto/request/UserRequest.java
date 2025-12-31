package com.example.quizapp.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank
        @Schema(description = "Full name", requiredMode = Schema.RequiredMode.REQUIRED)
        String fullName,
        @Email
        @NotBlank
        @Schema(description = "Email", requiredMode = Schema.RequiredMode.REQUIRED)
        String email
) {
}