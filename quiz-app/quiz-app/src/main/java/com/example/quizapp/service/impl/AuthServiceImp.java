package com.example.quizapp.service.impl;

import com.example.quizapp.constant.Constants;
import com.example.quizapp.dto.UserInformation;
import com.example.quizapp.dto.request.LoginRequest;
import com.example.quizapp.dto.request.RegisterRequest;
import com.example.quizapp.dto.response.LoginResponse;
import com.example.quizapp.entity.Role;
import com.example.quizapp.entity.User;
import com.example.quizapp.exception.AuthenticationException;
import com.example.quizapp.exception.BadRequestException;
import com.example.quizapp.exception.ResourceAlreadyExistsException;
import com.example.quizapp.exception.ResourceNotFoundException;
import com.example.quizapp.repository.RoleRepository;
import com.example.quizapp.repository.UserRepository;
import com.example.quizapp.service.AuthService;
import com.example.quizapp.service.TokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImp implements AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Value("${jwt.expiration}")
    private Long jwtExpirationInSec;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw AuthenticationException.invalidCredentials();
        } catch (DisabledException e) {
            throw AuthenticationException.accountDisabled();
        } catch (LockedException e) {
            throw AuthenticationException.accountLocked();
        }

        User user = userRepository.findByEmailAndActiveTrue(loginRequest.getEmail()).orElseThrow(AuthenticationException::userNotFound);
        log.info("User {} log in successfully", loginRequest.getEmail());
        return buildLoginResponse(user);
    }

    @Override
    public LoginResponse register(RegisterRequest registerRequest) {

        // Check if password as same as confirm password
        if (!registerRequest.password().equals(registerRequest.confirmPassword())) {
            throw BadRequestException.invalidRequest(registerRequest.confirmPassword());
        }

        // Check if username already exists
        if (userRepository.existsByEmail(registerRequest.email())) {
            throw ResourceAlreadyExistsException.usernameExists(registerRequest.email());
        }

        Role defaultRole = roleRepository.findByName(Constants.ROLE_USER)
                .orElseThrow(() -> {
                    return ResourceNotFoundException.roleNotFound(Constants.ROLE_USER);
                });

        // Create new user
        User newUser = new User();
        newUser.setFullName(registerRequest.fullName());
        newUser.setEmail(registerRequest.email());
        newUser.setPassword(passwordEncoder.encode(registerRequest.password()));
        newUser.setRoles(Set.of(defaultRole));
        newUser.setActive(true);

        User savedUser = userRepository.save(newUser);

        log.info("User {} log in successfully", registerRequest.email());
        return buildLoginResponse(savedUser);
    }

    private LoginResponse buildLoginResponse(User user) {
        Set<String> roleNames = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        String jwtToken = tokenService.generateToken(user, roleNames);

        UserInformation userInfo = UserInformation.builder()
                .id(user.getId())
                .username(user.getFullName())
                .email(user.getEmail())
                .status(user.getActive())
                .roles(roleNames)
                .build();

        return LoginResponse.builder()
                .token(jwtToken)
                .tokenType("Bearer")
                .expiresIn(jwtExpirationInSec)
                .user(userInfo)
                .build();
    }
}
