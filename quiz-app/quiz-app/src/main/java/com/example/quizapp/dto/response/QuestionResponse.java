package com.example.quizapp.dto.response;

import com.example.quizapp.entity.enums.QuestionType;

import java.util.List;
import java.util.UUID;

public record QuestionResponse(
        UUID id,
        String content,
        QuestionType type,
        Integer score,
        List<AnswerResponse> answers
) {}
