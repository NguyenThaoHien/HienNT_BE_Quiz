package com.example.quizapp.mapper;


import com.example.quizapp.dto.request.AnswerRequest;
import com.example.quizapp.dto.response.AnswerResponse;
import com.example.quizapp.entity.Answer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    Answer toEntity(AnswerRequest dto);

    AnswerResponse toResponse(Answer entity);
}