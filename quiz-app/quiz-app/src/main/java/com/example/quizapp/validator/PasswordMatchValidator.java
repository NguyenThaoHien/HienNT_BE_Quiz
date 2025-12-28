package com.example.quizapp.validator;

import com.example.quizapp.dto.request.RegisterRequest;
import com.example.quizapp.validator.anotation.PasswordMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, RegisterRequest> {

    @Override
    public boolean isValid(RegisterRequest request, ConstraintValidatorContext context) {
        if (request == null) return true;

        boolean match =
                request.password() != null &&
                        request.password().equals(request.confirmPassword());

        if (!match) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            "Confirm password không khớp"
                    )
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
        }

        return match;
    }
}
