package com.example.quizapp.service;

import com.example.quizapp.dto.request.QuestionRequest;
import com.example.quizapp.dto.response.QuestionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface QuestionService {

    QuestionResponse create(QuestionRequest request);

    Page<QuestionResponse> getAll(Pageable pageable);

    QuestionResponse getById(UUID id);

    void update(UUID id, QuestionRequest request);

    void delete(UUID id);
}
