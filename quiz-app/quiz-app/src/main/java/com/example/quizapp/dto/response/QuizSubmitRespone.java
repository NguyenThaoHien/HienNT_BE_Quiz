package com.example.quizapp.dto.response;

import java.util.UUID;

public record QuizSubmitRespone (

    UUID submissionId,


    Integer totalQuestions,


    Double score,


    Boolean passed
){}
