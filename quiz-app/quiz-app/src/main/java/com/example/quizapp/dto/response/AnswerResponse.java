package com.example.quizapp.dto.response;

import java.util.UUID;

public record AnswerResponse(
        UUID id,
        String content
) {}
