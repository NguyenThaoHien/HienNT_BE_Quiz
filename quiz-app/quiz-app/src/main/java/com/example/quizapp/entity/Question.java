package com.example.quizapp.entity;

import com.example.quizapp.entity.base.BaseEntity;
import com.example.quizapp.entity.enums.QuestionType;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @OneToMany(
            mappedBy = "question",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Answer> answers;
}
