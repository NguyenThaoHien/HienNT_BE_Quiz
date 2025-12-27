package com.example.quizapp.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record QuizRequest(
        @NotBlank
        @Size(max = 150)
        String title,

        @Size(max = 500)
        String description,

        @Min(1)
        Integer durationMinutes,

        List<UUID> questionIds
) {}
