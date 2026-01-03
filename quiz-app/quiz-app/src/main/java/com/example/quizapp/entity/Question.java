package com.example.quizapp.entity;

import com.example.quizapp.entity.base.BaseEntity;
import com.example.quizapp.entity.enums.QuestionType;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "questions")
public class Question extends BaseEntity {

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionType type;

    @Column(nullable = false)
    private Integer score;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "quiz_question",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "quiz_id")
    )
    private List<Quiz> quizzes = new ArrayList<>();

    @OneToMany(
            mappedBy = "question",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Answer> answers = new HashSet<>();
}

