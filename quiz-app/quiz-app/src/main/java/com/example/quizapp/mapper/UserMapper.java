package com.example.quizapp.mapper;

import com.example.quizapp.dto.response.UserResponse;
import com.example.quizapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(
            target = "roles",
            expression = "java(user.getRoles().stream().map(r -> r.getName()).collect(Collectors.toSet()))"
    )
    UserResponse toResponse(User user);
}