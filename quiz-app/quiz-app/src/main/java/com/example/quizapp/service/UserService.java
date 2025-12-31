package com.example.quizapp.service;

import com.example.quizapp.dto.request.UserRequest;
import com.example.quizapp.dto.response.UserResponse;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponse update(UserRequest userRequest);
    void delete(UUID id);
    Page<UserResponse> getAll(Pageable pageable);
Page<UserResponse> search(String name, Pageable pageable);
}
