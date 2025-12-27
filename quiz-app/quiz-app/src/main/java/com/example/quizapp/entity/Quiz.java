package com.example.quizapp.entity;


import com.example.quizapp.entity.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "quizzes")
@Getter @Setter
public class Quiz extends BaseEntity {

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false)
    private String title;

    @Size(max = 500)
    private String description;

    @Min(1)
    @Column(nullable = false)
    private Integer durationMinutes;

    @OneToMany(
            mappedBy = "quiz",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Question> questions;
}
