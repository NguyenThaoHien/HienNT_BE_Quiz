package com.example.quizapp.dto.response;

import java.util.List;
import java.util.UUID;

public record QuizResponse(
        UUID id,
        String title,
        String description,
        Integer durationMinutes,
        List<QuestionResponse> questions
) {}
