package com.example.quizapp.controller;

import com.example.quizapp.dto.request.QuestionRequest;
import com.example.quizapp.dto.response.ApiResponse;
import com.example.quizapp.dto.response.QuestionResponse;
import com.example.quizapp.service.QuestionService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/question")
@RequiredArgsConstructor
public class QuestionController {

    @Autowired
    private final QuestionService questionService;

    @PostMapping
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "News article created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = QuestionResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request data (validation errors)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - Requires REPORTER or ADMIN role", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Category not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)))
    })
    public ResponseEntity<ApiResponse<QuestionResponse>> createNewQuestion(
            @RequestBody QuestionRequest request
            ){
        // todo: authentication
        QuestionResponse response = questionService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(response, "News created successfully"));
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<Page<QuestionResponse>>> getListQuestion(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable
    ){
        Page<QuestionResponse> listQuestion = questionService.getAll(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(listQuestion, "Get all question successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<QuestionResponse>> getQuestionAndAnswer(@PathVariable String id){
        QuestionResponse question = questionService.getById(UUID.fromString(id));
        return ResponseEntity.ok(ApiResponse.success(question, "Get question by id"));
    }

    public ResponseEntity<ApiResponse<QuestionResponse>> updateQuestion(
            @PathVariable String id
            @RequestBody Que
    )
}
