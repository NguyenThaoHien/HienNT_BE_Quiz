package com.example.quizapp.entity;

import com.example.quizapp.entity.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "answers")
@Getter @Setter
public class Answer extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean correct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;
}