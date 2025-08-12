package com.exemplo.taskmanager.service;

import com.exemplo.taskmanager.model.Task;
import com.exemplo.taskmanager.model.User;
import com.exemplo.taskmanager.repository.TaskRepository;
import com.exemplo.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<Task> getTasksForUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return taskRepository.findByUser(user);
    }

    public Task createTask(String userEmail, Task task) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        task.setUser(user);
        return taskRepository.save(task);
    }

    public Task updateTask(String userEmail, Long taskId, Task taskUpdates) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        if (!task.getUser().getEmail().equals(user.getEmail())) {
            throw new RuntimeException("Usuário não autorizado a atualizar essa tarefa");
        }

        task.setTitle(taskUpdates.getTitle());
        task.setDescription(taskUpdates.getDescription());
        task.setCompleted(taskUpdates.isCompleted());

        return taskRepository.save(task);
    }

    public void deleteTask(String userEmail, Long taskId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        if (!task.getUser().getEmail().equals(user.getEmail())) {
            throw new RuntimeException("Usuário não autorizado a deletar essa tarefa");
        }

        taskRepository.delete(task);
    }
}