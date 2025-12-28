package com.example.quizapp.dto.request;

import com.example.quizapp.validator.PasswordMatchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

public record RegisterRequest(
        @Email @NotBlank String email,
        @NotBlank String fullName,
        @Size(min = 8) String password,
        @NotBlank
        String confirmPassword
) {}



