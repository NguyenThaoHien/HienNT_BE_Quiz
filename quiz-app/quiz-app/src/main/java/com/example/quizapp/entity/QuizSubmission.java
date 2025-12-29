package com.example.quizapp.entity;

import com.example.quizapp.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "quiz_submissions")
@Getter @Setter
public class QuizSubmission extends BaseEntity {

    @Column(nullable = false)
    private Double score;

    @Column(nullable = false)
    private Timestamp submissionTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
}