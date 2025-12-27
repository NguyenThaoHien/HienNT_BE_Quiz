package com.example.quizapp.repository;

import com.example.quizapp.entity.Quiz;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuizRepository extends JpaRepository<Quiz, UUID> {

    @EntityGraph(attributePaths = {
            "questions",
            "questions.answers"
    })
    List<Quiz> findAllByActiveTrue();

    @EntityGraph(attributePaths = {
            "questions",
            "questions.answers"
    })
    Optional<Quiz> findByIdAndActiveTrue(UUID id);
}
