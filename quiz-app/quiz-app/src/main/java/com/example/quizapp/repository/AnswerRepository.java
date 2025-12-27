package com.example.quizapp.repository;

import com.example.quizapp.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AnswerRepository extends JpaRepository<Answer, UUID> {

    List<Answer> findByQuestion_IdAndActiveTrue(UUID questionId);
}
