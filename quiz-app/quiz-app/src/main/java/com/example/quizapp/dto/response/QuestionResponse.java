package com.example.quizapp.dto.response;

import com.example.quizapp.entity.enums.QuestionType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.UUID;

@Schema(description = "Question response DTO")
public record QuestionResponse(

        @Schema(
                description = "Unique identifier of the question",
                example = "550e8400-e29b-41d4-a716-446655440000",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        UUID id,

        @Schema(
                description = "Content of the question",
                example = "What is the capital of Vietnam?",
                requiredMode = Schema.RequiredMode.REQUIRED,
                minLength = 3,
                maxLength = 255
        )
        String content,

        @Schema(
                description = "Type of the question",
                example = "SINGLE_CHOICE",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        QuestionType type,

        @Schema(
                description = "Score awarded for the correct answer",
                example = "10",
                minimum = "0",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED
        )
        Integer score,

        @Schema(
                description = "List of answers for the question",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED
        )
        List<AnswerResponse> answers

) {}
