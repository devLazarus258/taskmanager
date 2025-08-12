package com.exemplo.taskmanager.controller;

import com.exemplo.taskmanager.dto.*;
import com.exemplo.taskmanager.repository.UserRepository;
import com.exemplo.taskmanager.security.JwtProvider;
import com.exemplo.taskmanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager,
            JwtProvider jwtProvider,
            UserService userService,
            UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email já está em uso");
        }
        userService.register(request.getName(), request.getEmail(), request.getPassword());
        return ResponseEntity.ok("Usuário registado com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String token = jwtProvider.generateToken(authentication);
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
