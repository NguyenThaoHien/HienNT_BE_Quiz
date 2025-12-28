package com.example.quizapp.mapper;


import com.example.quizapp.dto.request.AnswerRequest;
import com.example.quizapp.dto.response.AnswerResponse;
import com.example.quizapp.entity.Answer;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring" , imports = Collectors.class)
public interface AnswerMapper {

    Answer toEntity(AnswerRequest dto);

    AnswerResponse toResponse(Answer entity);
}