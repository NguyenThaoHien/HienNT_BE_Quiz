package com.example.quizapp.dto.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record QuizSubmitRequest (



            @NotNull(message = "User ID is required")
            UUID userId,


            @NotNull(message = "Quiz ID is required")
            UUID quizId,


            @NotEmpty(message = "Answers cannot be empty")
            @Valid
            List<QuestionAnswerDTO> answers
    ) {


        public record QuestionAnswerDTO(


                @NotNull(message = "Question ID is required")
                UUID questionId,


                @NotEmpty(message = "Answer IDs cannot be empty")
                List<UUID> answerIds
        ) {}
    }

