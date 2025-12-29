package com.example.quizapp.service;

import com.example.quizapp.dto.request.QuizRequest;
import com.example.quizapp.dto.response.QuizResponse;

import java.util.List;
import java.util.UUID;

public interface QuizzService {
    QuizResponse create(QuizRequest quizRequest);
    void delete(UUID uuid);
    void addQuestionToQuizz(UUID quizzID, List<UUID> questions);
}
