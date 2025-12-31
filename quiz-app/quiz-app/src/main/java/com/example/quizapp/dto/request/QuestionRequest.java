package com.example.quizapp.dto.request;

import com.example.quizapp.entity.enums.QuestionType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record QuestionRequest(
        @NotBlank @NotNull
        @Schema(description = "Question content", example = "What is correct answer of 1 + 1?", requiredMode = Schema.RequiredMode.REQUIRED)
        String content,
        @Schema(description = "Type of question", example = "SINGLE_CHOICE", requiredMode = Schema.RequiredMode.REQUIRED)
        QuestionType type,
        @Min(1)
        @Schema(description = "Score of this question", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
        Integer score,
        @Schema(description = "List answer of question", requiredMode = Schema.RequiredMode.REQUIRED)
        List<@Valid AnswerRequest> answers
) {}