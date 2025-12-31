package com.example.quizapp.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record AnswerResponse(
        @Schema(description = "Id of answer", requiredMode = Schema.RequiredMode.REQUIRED)
        UUID id,
        @Schema(description = "Answer content", requiredMode = Schema.RequiredMode.REQUIRED)
        String content
) {}
