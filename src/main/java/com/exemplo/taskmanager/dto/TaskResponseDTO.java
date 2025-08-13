package com.exemplo.taskmanager.dto;

import lombok.*;

import java.util.Date; // ou Instant se preferir

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private String userEmail;
    private Date createdAt;  // ou Instant
}
