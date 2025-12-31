package com.example.quizapp.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record QuizRequest(
        @NotBlank
        @Size(max = 150)
        @Schema(description = "Quiz title", requiredMode = Schema.RequiredMode.REQUIRED)
        String title,

        @Size(max = 500)
        @Schema(description = "Quiz description", requiredMode = Schema.RequiredMode.REQUIRED)
        String description,

        @Min(1)
        @Schema(description = "Quiz duration (minutes)", requiredMode = Schema.RequiredMode.REQUIRED)
        Integer durationMinutes,

        @Schema(description = "List of question id in quiz", requiredMode = Schema.RequiredMode.REQUIRED)
        List<UUID> questionIds
) {}
