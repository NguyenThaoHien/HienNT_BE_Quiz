package com.example.quizapp.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest (
    @NotBlank
    String fullName,
     @Email @NotBlank String email
    ){}