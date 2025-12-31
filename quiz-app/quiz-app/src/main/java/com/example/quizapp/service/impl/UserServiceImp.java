package com.example.quizapp.service.impl;

import com.example.quizapp.dto.request.UserRequest;
import com.example.quizapp.dto.response.UserResponse;
import com.example.quizapp.entity.Question;
import com.example.quizapp.entity.User;
import com.example.quizapp.mapper.UserMapper;
import com.example.quizapp.repository.UserRepository;
import com.example.quizapp.service.UserService;
import com.example.quizapp.specifications.UserSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImp(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse update(UserRequest userRequest) {
        User user = userRepository.findByEmailAndActiveTrue(userRequest.email()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setFullName(userRequest.fullName());
        User newUser = userRepository.save(user);
        return userMapper.toResponse(newUser);

    }

    @Override
    public void delete(UUID id) {
        User user = userRepository.findByIdAndActiveTrue(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setActive(false);
        log.info("User with email {} deleted successfully", user.getEmail());
        userRepository.save(user);
    }

    @Override
    public Page<UserResponse> getAll(Pageable pageable) {
        Page<User> page = userRepository.findAllByActiveTrue(pageable);
        return page.map(userMapper::toResponse);
    }

    @Override
    public Page<UserResponse> search(String name, Pageable pageable) {
        Specification<User> spec = Specification
                .where(UserSpecification.hasFullName(name));
        Page<User> page = userRepository.findAll(spec, pageable);
        return page.map(userMapper::toResponse);
    }
}
