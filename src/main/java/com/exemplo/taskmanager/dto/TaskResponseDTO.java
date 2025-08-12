package com.exemplo.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private String userEmail;
}
