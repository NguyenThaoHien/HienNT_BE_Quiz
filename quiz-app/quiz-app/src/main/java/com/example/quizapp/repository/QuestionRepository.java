package com.example.quizapp.repository;

import com.example.quizapp.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {

    Page<Question> findAllByActiveTrue(Pageable pageable);

    Optional<Question> findByIdAndActiveTrue(UUID id);


}

