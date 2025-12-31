package com.example.quizapp.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record AnswerRequest(

        @NotBlank
        @Schema(description = "Answer content", example = "", minLength = 3, requiredMode = Schema.RequiredMode.REQUIRED)
        String content,

        @Schema(description = "Username of the account", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
        boolean correct
) {}
