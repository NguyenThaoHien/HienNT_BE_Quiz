package com.example.quizapp.dto.request;

import com.example.quizapp.entity.enums.QuestionType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record QuestionRequest(
        @NotBlank String content,
        QuestionType type,
        @Min(1) Integer score,
        List<@Valid AnswerRequest> answers
) {}