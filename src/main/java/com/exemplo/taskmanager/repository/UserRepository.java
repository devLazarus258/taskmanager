package com.exemplo.taskmanager.repository;

import java.util.Optional;
import com.exemplo.taskmanager.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findByEmail(String email);
    boolean exexistsByEmail(String email);
}