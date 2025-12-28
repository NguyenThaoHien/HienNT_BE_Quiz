package com.example.quizapp.validator.anotation;

import com.example.quizapp.validator.PasswordMatchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatch {
    String message() default "Password và ConfirmPassword không khớp";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
