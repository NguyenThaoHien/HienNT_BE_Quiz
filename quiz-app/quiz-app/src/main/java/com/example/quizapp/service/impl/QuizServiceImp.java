package com.example.quizapp.service.impl;

import com.example.quizapp.dto.request.QuizRequest;
import com.example.quizapp.dto.request.QuizSubmitRequest;
import com.example.quizapp.dto.response.QuizResponse;
import com.example.quizapp.dto.response.QuizSubmitRespone;
import com.example.quizapp.entity.*;
import com.example.quizapp.entity.enums.QuestionType;
import com.example.quizapp.exception.ResourceNotFoundException;
import com.example.quizapp.mapper.QuizMapper;
import com.example.quizapp.repository.QuestionRepository;
import com.example.quizapp.repository.QuizRepository;
import com.example.quizapp.repository.QuizSubmissionRepository;
import com.example.quizapp.repository.UserRepository;
import com.example.quizapp.service.QuizzService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional

public class QuizServiceImp implements QuizzService {
private final QuizRepository quizRepository;
private final QuestionRepository questionRepository;
private final QuizMapper quizMapper;
private final UserRepository userRepository;
private final QuizSubmissionRepository quizSubmissionRepository;
@Autowired
    public QuizServiceImp(QuizRepository quizRepository, QuestionRepository questionRepository, QuizMapper quizMapper, UserRepository userRepository, QuizSubmissionRepository quizSubmissionRepository) {
        this.quizRepository = quizRepository;
    this.questionRepository = questionRepository;
    this.quizMapper = quizMapper;
    this.userRepository = userRepository;
    this.quizSubmissionRepository = quizSubmissionRepository;
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

    @Override
    public QuizResponse update(QuizRequest quizRequest, UUID uuid) {
Quiz quiz  = quizRepository.findByIdAndActiveTrue(uuid).orElseThrow(()->new EntityNotFoundException("Quiz not found"));
quizMapper.updateEntity(quiz,quizRequest);
Quiz newQuiz = quizRepository.save(quiz);
return quizMapper.toResponse(newQuiz);
    }

    @Override
    public Page<QuizResponse> getAll(Pageable pageable) {
        Page<Quiz> page = quizRepository.findAllByActiveTrue(pageable);
        return page.map(quizMapper::toResponse);
    }

    @Override
    public QuizSubmitRespone submit(QuizSubmitRequest quizSubmitRequest) {
        User user = userRepository.findById(quizSubmitRequest.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", quizSubmitRequest.userId()));

        Quiz quiz = quizRepository.findByIdWithQuestionsAndAnswers(quizSubmitRequest.quizId())
                .orElseThrow(() -> new ResourceNotFoundException("Quiz", "id", quizSubmitRequest.quizId()));

        if (!Boolean.TRUE.equals(quiz.getActive())) {
            throw new IllegalStateException("Quiz is not active");
        }

        List<Question> quizQuestions = quiz.getQuestions();

        if (quizQuestions == null || quizQuestions.isEmpty()) {
            throw new IllegalStateException("Quiz has no questions");
        }

        double achievedScore = 0.0;
        double totalScore = 0.0;
        Map<UUID, List<UUID>> submittedAnswersMap = quizSubmitRequest.answers().stream()
                .collect(Collectors.toMap(
                        QuizSubmitRequest.QuestionAnswerDTO::questionId,
                        QuizSubmitRequest.QuestionAnswerDTO::answerIds
                ));
        for (Question question : quizQuestions) {
            totalScore += question.getScore();

            List<UUID> submittedAnswerIds = submittedAnswersMap.getOrDefault(question.getId(), Collections.emptyList());

            List<UUID> correctAnswerIds = question.getAnswers().stream()
                    .filter(Answer::getCorrect)
                    .map(Answer::getId)
                    .toList();

            boolean isCorrect = checkAnswer(question, submittedAnswerIds, correctAnswerIds);

            if (isCorrect) {
                achievedScore += question.getScore();
            }
        }

        double passPercentage=50;
        boolean passed = (achievedScore / totalScore) * 100.0 >= passPercentage;

        QuizSubmission submission = new QuizSubmission();
        submission.setUser(user);
        submission.setQuiz(quiz);
        submission.setScore(achievedScore);
        submission.setSubmissionTime(LocalDateTime.now());
        submission = quizSubmissionRepository.save(submission);



        return new QuizSubmitRespone(
                submission.getId(),
                quizQuestions.size(),
                achievedScore,
                passed
        );
    }

    private boolean checkAnswer(Question question, List<UUID> submittedAnswerIds, List<UUID> correctAnswerIds) {
        if (question.getType() == QuestionType.SINGLE_CHOICE) {
            // Single choice
            return submittedAnswerIds.size() == 1 &&
                    correctAnswerIds.contains(submittedAnswerIds.get(0));
        } else {
            // Multiple choice
            Set<UUID> submittedSet = new HashSet<>(submittedAnswerIds);
            Set<UUID> correctSet = new HashSet<>(correctAnswerIds);
            return submittedSet.equals(correctSet);
        }
    }
}
