package com.exemplo.taskmanager.controller;

import com.exemplo.taskmanager.dto.*;
import com.exemplo.taskmanager.model.Task;
import com.exemplo.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<TaskResponseDTO> getTasks(Authentication authentication) {
        String email = authentication.getName();
        return service.getTasksForUser(email).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public TaskResponseDTO createTask(Authentication authentication,
            @Valid @RequestBody TaskRequestDTO dto) {
        String email = authentication.getName();
        Task t = Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .completed(dto.isCompleted())
                .build();
        Task saved = service.createTask(email, t);
        return toDto(saved);
    }

    @PutMapping("/{id}")
    public TaskResponseDTO updateTask(Authentication authentication,
            @PathVariable Long id,
            @Valid @RequestBody TaskRequestDTO dto) {
        String email = authentication.getName();
        Task updated = Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .completed(dto.isCompleted())
                .build();
        Task saved = service.updateTask(email, id, updated);
        return toDto(saved);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(Authentication authentication, @PathVariable Long id) {
        String email = authentication.getName();
        service.deleteTask(email, id);
    }

    private TaskResponseDTO toDto(Task t) {
        return new TaskResponseDTO(t.getId(), t.getTitle(), t.getDescription(), t.isCompleted());
    }
}
