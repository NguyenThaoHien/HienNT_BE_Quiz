package com.example.quizapp.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record QuizSubmitRequest(
        @NotNull(message = "User ID is required")
        @Schema(description = "User id", requiredMode = Schema.RequiredMode.REQUIRED)
        UUID userId,
        @NotNull(message = "Quiz ID is required")
        @Schema(description = "Quiz id", requiredMode = Schema.RequiredMode.REQUIRED)
        UUID quizId,
        @NotEmpty(message = "Answers cannot be empty")
        @Valid
        @Schema(description = "List of question and answer", requiredMode = Schema.RequiredMode.REQUIRED)
        List<QuestionAnswerDTO> answers
) {
    public record QuestionAnswerDTO(
            @NotNull(message = "Question ID is required")
            UUID questionId,
            @NotEmpty(message = "Answer IDs cannot be empty")
            List<UUID> answerIds
    ) {
    }
}

