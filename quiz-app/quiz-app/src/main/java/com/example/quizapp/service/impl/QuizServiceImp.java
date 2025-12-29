package com.example.quizapp.service.impl;

import com.example.quizapp.dto.request.QuizRequest;
import com.example.quizapp.dto.response.QuizResponse;
import com.example.quizapp.entity.Question;
import com.example.quizapp.entity.Quiz;
import com.example.quizapp.mapper.QuizMapper;
import com.example.quizapp.repository.QuestionRepository;
import com.example.quizapp.repository.QuizRepository;
import com.example.quizapp.service.QuizzService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
@Service
@Transactional

public class QuizServiceImp implements QuizzService {
private final QuizRepository quizRepository;
private final QuestionRepository questionRepository;
private final QuizMapper quizMapper;
@Autowired
    public QuizServiceImp(QuizRepository quizRepository, QuestionRepository questionRepository, QuizMapper quizMapper) {
        this.quizRepository = quizRepository;
    this.questionRepository = questionRepository;
    this.quizMapper = quizMapper;
}

    @Override
    public QuizResponse create(QuizRequest quizRequest) {
        Quiz quiz = quizMapper.toEntity(quizRequest);
        Quiz newquiz = quizRepository.save(quiz);
        return quizMapper.toResponse(newquiz);
    }

    @Override
    public void delete(UUID uuid) {
    Quiz quiz = quizRepository.findByIdAndActiveTrue(uuid).orElseThrow(()->new EntityNotFoundException("Quizz not found"));
    quiz.setActive(false);
    quizRepository.save(quiz);

    }

    @Override
    public void addQuestionToQuizz(UUID quizzID, List<UUID> questions) {
        Quiz quiz = quizRepository.findByIdAndActiveTrue(quizzID).orElseThrow(()->new EntityNotFoundException("Quizz not found"));
        List<Question> questionList = questionRepository.findAllByIdInAndActiveTrue(questions);
        quiz.setQuestions(questionList);
        quizRepository.save(quiz);
    }
}
