package com.example.quizapp.mapper;

import com.example.quizapp.dto.request.QuestionRequest;
import com.example.quizapp.dto.response.QuestionResponse;
import com.example.quizapp.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = AnswerMapper.class, imports = Collectors.class)
public interface QuestionMapper {


    Question toEntity(QuestionRequest dto);

    QuestionResponse toResponse(Question entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "quizzes", ignore = true)
    void updateEntity(@org.mapstruct.MappingTarget Question question, QuestionRequest dto);
}
