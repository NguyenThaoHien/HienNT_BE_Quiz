package com.example.quizapp.mapper;

import com.example.quizapp.dto.response.QuizResponse;
import com.example.quizapp.entity.Quiz;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = QuestionMapper.class)
public interface QuizMapper {

    QuizResponse toResponse(Quiz quiz);
}
