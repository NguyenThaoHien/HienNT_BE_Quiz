package com.example.quizapp.mapper;

import com.example.quizapp.dto.request.QuizRequest;
import com.example.quizapp.dto.response.QuizResponse;
import com.example.quizapp.entity.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = QuestionMapper.class)
public interface QuizMapper {
Quiz toEntity(QuizRequest quizRequest);
    QuizResponse toResponse(Quiz quiz);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "questions", ignore = true)
    void updateEntity(@org.mapstruct.MappingTarget Quiz quiz, QuizRequest quizRequest);
}
