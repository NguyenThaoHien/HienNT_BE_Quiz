package com.example.quizapp.dto.request;


import jakarta.validation.constraints.NotBlank;

public record AnswerRequest(

        @NotBlank String content,
        boolean correct
) {}
