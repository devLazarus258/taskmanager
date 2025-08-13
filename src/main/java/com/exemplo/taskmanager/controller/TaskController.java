package com.exemplo.taskmanager.controller;

import com.exemplo.taskmanager.dto.TaskRequestDTO;
import com.exemplo.taskmanager.dto.TaskResponseDTO;
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
        List<Task> tasks = service.getTasksForUser(email);
        return tasks.stream().map(this::toDto).collect(Collectors.toList());
    }

    @PostMapping
    public TaskResponseDTO createTask(Authentication authentication, @Valid @RequestBody TaskRequestDTO dto) {
        String email = authentication.getName();
        Task task = Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .completed(dto.isCompleted())
                .build();
        Task saved = service.createTask(email, task);
        return toDto(saved);
    }

    @PutMapping("/{id}")
    public TaskResponseDTO updateTask(Authentication authentication,
            @PathVariable Long id,
            @Valid @RequestBody TaskRequestDTO dto) {
        String email = authentication.getName();
        Task taskUpdates = Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .completed(dto.isCompleted())
                .build();
        Task updated = service.updateTask(email, id, taskUpdates);
        return toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(Authentication authentication, @PathVariable Long id) {
        String email = authentication.getName();
        service.deleteTask(email, id);
    }

    private TaskResponseDTO toDto(Task task) {
        return TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .completed(task.isCompleted())
                .userEmail(task.getUser() != null ? task.getUser().getEmail() : null)
                .createdAt(task.getCreatedAt()) 
                .build();
    }
}
