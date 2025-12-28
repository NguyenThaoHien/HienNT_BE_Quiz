package com.example.quizapp.service.impl;

import com.example.quizapp.dto.request.QuestionRequest;
import com.example.quizapp.dto.response.QuestionResponse;
import com.example.quizapp.entity.Question;
import com.example.quizapp.mapper.QuestionMapper;
import com.example.quizapp.mapper.UserMapper;
import com.example.quizapp.repository.QuestionRepository;
import com.example.quizapp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Service
@Transactional
@RequiredArgsConstructor
public class QuestionServiceImp implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Override
    public QuestionResponse create(QuestionRequest request) {
        Question question = questionMapper.toEntity(request);
        Question newsq = questionRepository.save(question);
        return questionMapper.toResponse(newsq);
    }

    @Override
    public Page<QuestionResponse> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public QuestionResponse getById(UUID id) {
        return null;
    }

    @Override
    public void update(UUID id, QuestionRequest request) {

    }

    @Override
    public void delete(UUID id) {

    }
}
