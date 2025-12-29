package com.example.quizapp.controller;


import com.example.quizapp.dto.request.QuestionRequest;
import com.example.quizapp.dto.request.QuizRequest;
import com.example.quizapp.dto.response.ApiResponse;
import com.example.quizapp.dto.response.QuestionResponse;
import com.example.quizapp.dto.response.QuizResponse;
import com.example.quizapp.service.QuizzService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quizz")
@Transactional
public class QuizzController {
    private final QuizzService quizzService;

    @Autowired
    public QuizzController(QuizzService quizzService) {
        this.quizzService = quizzService;
    }

    @PostMapping
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Quizz updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = QuestionResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - Requires USER or ADMIN role", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Quizz not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)))
    })
    public ResponseEntity<ApiResponse<QuizResponse>> createNewQuestion(
            @Valid @RequestBody QuizRequest request
    ){
        QuizResponse response = quizzService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(response, "Quizz created successfully"));
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Delete quizz successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = QuestionResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - Requires USER or ADMIN role", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Quizz not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)))
    })
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable String id){
        quizzService.delete(UUID.fromString(id));
        return ResponseEntity.ok(ApiResponse.success(id, "Delete quizz successfully"));
    }

    @PostMapping("/{id}")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Quizz updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = QuestionResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - Requires USER or ADMIN role", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Quizz not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)))
    })
    public ResponseEntity<ApiResponse<String>> addQuestionToQuizz (
            @PathVariable String quizzID,
            @RequestBody List<UUID> questionID
            ){
            quizzService.addQuestionToQuizz(UUID.fromString(quizzID),questionID);
        return ResponseEntity.ok(ApiResponse.success(quizzID, "Quizz update successfully"));
    }
}
