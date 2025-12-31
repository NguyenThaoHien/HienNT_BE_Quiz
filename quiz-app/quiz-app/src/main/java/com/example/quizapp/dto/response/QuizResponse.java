package com.example.quizapp.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.UUID;

@Schema(description = "Quiz response DTO")
public record QuizResponse(

        @Schema(
                description = "Unique identifier of the quiz",
                example = "c1b2d3e4-f5a6-7890-b123-456789abcdef",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        UUID id,

        @Schema(
                description = "Title of the quiz",
                example = "Java Basics Quiz",
                requiredMode = Schema.RequiredMode.REQUIRED,
                minLength = 3,
                maxLength = 100
        )
        String title,

        @Schema(
                description = "Description of the quiz",
                example = "A quiz to test basic Java knowledge",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                maxLength = 255
        )
        String description,

        @Schema(
                description = "Duration of the quiz in minutes",
                example = "30",
                minimum = "1",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Integer durationMinutes,

        @Schema(
                description = "List of questions included in the quiz",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED
        )
        List<QuestionResponse> questions

) {}
