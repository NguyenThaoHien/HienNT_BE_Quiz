package com.example.quizapp.mapper;

import com.example.quizapp.dto.request.QuestionRequest;
import com.example.quizapp.dto.response.QuestionResponse;
import com.example.quizapp.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AnswerMapper.class)
public interface QuestionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "quiz", ignore = true)
    Question toEntity(QuestionRequest dto);

    QuestionResponse toResponse(Question entity);
}
