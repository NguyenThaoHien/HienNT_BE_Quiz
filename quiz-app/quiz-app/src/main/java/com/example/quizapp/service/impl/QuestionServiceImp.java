package com.example.quizapp.service.impl;

import com.example.quizapp.dto.request.QuestionRequest;
import com.example.quizapp.dto.response.QuestionResponse;
import com.example.quizapp.entity.Question;
import com.example.quizapp.exception.ResourceNotFoundException;
import com.example.quizapp.mapper.QuestionMapper;
import com.example.quizapp.mapper.UserMapper;
import com.example.quizapp.repository.QuestionRepository;
import com.example.quizapp.service.QuestionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@Slf4j
public class QuestionServiceImp implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Autowired
    public QuestionServiceImp(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    @Override
    public QuestionResponse create(QuestionRequest request) {
        Question question = questionMapper.toEntity(request);
        if (question.getAnswers() != null) {
            question.getAnswers().forEach(answer -> {
                answer.setQuestion(question);
            });
        }
        Question newsq = questionRepository.save(question);
        return questionMapper.toResponse(newsq);
    }

    @Override
    public Page<QuestionResponse> getAll(Pageable pageable) {
        Page<Question> page = questionRepository.findAllByActiveTrue(pageable);
        return page.map(questionMapper::toResponse);
    }

    @Override
    public QuestionResponse getById(UUID id) {
        Question question = questionRepository.findByIdAndActiveTrue(id).orElseThrow(() -> new EntityNotFoundException("Question not found"));
        return questionMapper.toResponse(question);
    }

    @Override
    public QuestionResponse update(UUID id, QuestionRequest request) {
        Question existing = questionRepository.findById(id).orElseThrow(() -> ResourceNotFoundException.questionNotFound(id));
        questionMapper.updateEntity(existing, request);

        Question saved = questionRepository.save(existing);
        return questionMapper.toResponse(saved);
    }

    @Override
    public void delete(UUID id) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Question not found"));
        question.setActive(false);
        questionRepository.save(question);
    }
}
