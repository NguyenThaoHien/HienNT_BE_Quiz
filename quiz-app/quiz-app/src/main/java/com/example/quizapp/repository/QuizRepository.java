package com.example.quizapp.repository;

import com.example.quizapp.entity.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;
import java.util.UUID;

public interface QuizRepository extends JpaRepository<Quiz, UUID> {
    @Query("SELECT q FROM Quiz q LEFT JOIN FETCH q.questions qs LEFT JOIN FETCH qs.answers WHERE q.id = :id")
    Optional<Quiz> findByIdWithQuestionsAndAnswers(@Param("id") UUID id);
    @EntityGraph(attributePaths = {
            "questions",
            "questions.answers"
    })
    Page<Quiz> findAllByActiveTrue(Pageable pageable);

    @EntityGraph(attributePaths = {
            "questions",
            "questions.answers"
    })
    Optional<Quiz> findByIdAndActiveTrue(UUID id);
}
