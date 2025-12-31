package com.example.quizapp.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Quiz submission result response")
public record QuizSubmitRespone(

        @Schema(
                description = "Unique identifier of the quiz submission",
                example = "9f1c2b3a-4d5e-6789-a123-b4567890cdef",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        UUID submissionId,

        @Schema(
                description = "Total number of questions in the quiz",
                example = "20",
                minimum = "0",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Integer totalQuestions,

        @Schema(
                description = "Final score achieved by the user",
                example = "8.5",
                minimum = "0",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Double score,

        @Schema(
                description = "Indicates whether the user passed the quiz",
                example = "true",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Boolean passed

) {}
