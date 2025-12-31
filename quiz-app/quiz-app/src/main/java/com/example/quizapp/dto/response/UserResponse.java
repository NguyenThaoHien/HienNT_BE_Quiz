package com.example.quizapp.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;
import java.util.UUID;

@Schema(description = "User information response")
public record UserResponse(

        @Schema(
                description = "Unique identifier of the user",
                example = "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        UUID id,

        @Schema(
                description = "Email address of the user",
                example = "user@example.com",
                requiredMode = Schema.RequiredMode.REQUIRED,
                format = "email"
        )
        String email,

        @Schema(
                description = "Full name of the user",
                example = "Nguyen Van A",
                requiredMode = Schema.RequiredMode.REQUIRED,
                minLength = 3,
                maxLength = 100
        )
        String fullName,

        @Schema(
                description = "Roles assigned to the user",
                example = "[\"ROLE_USER\", \"ROLE_ADMIN\"]",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Set<String> roles

) {}
