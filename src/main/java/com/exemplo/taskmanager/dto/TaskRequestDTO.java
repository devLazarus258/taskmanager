package com.exemplo.taskmanager.dto;

import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO {
    @NotBlank
    private String title;
    private String description;
    boolean completed = false;
}
