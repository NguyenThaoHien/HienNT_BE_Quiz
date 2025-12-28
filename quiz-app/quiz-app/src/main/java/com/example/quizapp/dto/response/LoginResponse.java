package com.example.quizapp.dto.response;

import com.example.quizapp.dto.UserInformation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    @Schema(description = "JWT access token for authenticating subsequent requests", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    @Schema(description = "Token type, always 'Bearer'", example = "Bearer")
    private String tokenType;

    @Schema(description = "Token expiration time in seconds", example = "3600")
    private Long expiresIn;

    @Schema(description = "Authenticated user information")
    private UserInformation user;
}
