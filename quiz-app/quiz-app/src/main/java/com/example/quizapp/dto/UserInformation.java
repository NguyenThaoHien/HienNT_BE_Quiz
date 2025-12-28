package com.example.quizapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInformation {
    private UUID id;
    private String username;
    private String email;
    private boolean status;
    private Set<String> roles;
}
