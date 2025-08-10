package com.exemplo.taskmanager.repository;

import java.util.Optional;
import java.util.List;

import com.exemplo.taskmanager.model.Task;
import com.exemplo.taskmanager.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);

    Optional<Task> findByIdAdnUser(Long id, User ser);
}
