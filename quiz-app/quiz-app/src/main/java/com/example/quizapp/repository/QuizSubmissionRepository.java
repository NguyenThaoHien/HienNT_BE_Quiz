package com.example.quizapp.repository;

import com.example.quizapp.entity.QuizSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, UUID> {

    List<QuizSubmission> findByUser_Id(UUID userId);

    boolean existsByUser_IdAndQuiz_Id(UUID userId, UUID quizId);
}