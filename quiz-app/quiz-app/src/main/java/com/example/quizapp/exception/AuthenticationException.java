package com.example.quizapp.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationException extends BaseException{
    private static final String DEFAULT_ERROR_CODE = "AUTH_001";

    public AuthenticationException(String message) {
        super(message, HttpStatus.UNAUTHORIZED, DEFAULT_ERROR_CODE);
    }

    public AuthenticationException(String message, String errorCode) {
        super(message, HttpStatus.UNAUTHORIZED, errorCode);
    }

    public static AuthenticationException invalidCredentials() {
        return new AuthenticationException("Invalid username or password", "AUTH_002");
    }

    public static AuthenticationException userNotFound() {
        return new AuthenticationException("User not found", "AUTH_003");
    }

    public static AuthenticationException accountDisabled() {
        return new AuthenticationException("Account is disabled", "AUTH_004");
    }

    public static AuthenticationException accountLocked() {
        return new AuthenticationException("Account is locked", "AUTH_005");
    }}
