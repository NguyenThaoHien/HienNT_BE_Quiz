package com.example.quizapp.mapper;

import com.example.quizapp.dto.request.QuizRequest;
import com.example.quizapp.dto.response.QuizResponse;
import com.example.quizapp.entity.Quiz;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = QuestionMapper.class)
public interface QuizMapper {
Quiz toEntity(QuizRequest quizRequest);
    QuizResponse toResponse(Quiz quiz);
}
