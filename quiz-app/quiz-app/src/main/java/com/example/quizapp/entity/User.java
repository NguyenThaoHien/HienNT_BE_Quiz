package com.example.quizapp.entity;


import com.example.quizapp.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {


    @Column(nullable = false, unique = true)
    private String email;


    @Column(nullable = false)
    private String password;


    @Column(nullable = false)
    private String fullName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}