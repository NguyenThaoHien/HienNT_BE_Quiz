package com.example.quizapp.service;

import com.example.quizapp.dto.request.QuizRequest;
import com.example.quizapp.dto.request.QuizSubmitRequest;
import com.example.quizapp.dto.response.QuizDetailResponse;
import com.example.quizapp.dto.response.QuizResponse;
import com.example.quizapp.dto.response.QuizSubmitRespone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface QuizzService {
    QuizDetailResponse create(QuizRequest quizRequest);
    void delete(UUID uuid);
    void addQuestionToQuizz(UUID quizzID, List<UUID> questions);
    QuizResponse update(QuizRequest quizRequest, UUID uuid);
    Page<QuizResponse> getAll(Pageable pageable);
    QuizSubmitRespone submit(QuizSubmitRequest quizSubmitRequest);
}
