package com.exemplo.taskmanager.service;

import com.exemplo.taskmanager.model.User;
import com.exemplo.taskmanager.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String name, String email, String plaintextPassword) {
        if (userRepository.exexistsByEmail(email)) {
            throw new RuntimeException("Email já em uso");
        }
        User user = User.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(plaintextPassword))
                .build();
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
