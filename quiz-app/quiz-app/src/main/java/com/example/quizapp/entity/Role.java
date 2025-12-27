package com.example.quizapp.entity;

import com.example.quizapp.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter @Setter
public class Role extends BaseEntity {

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name; // ROLE_USER, ROLE_ADMIN
}
