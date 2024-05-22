package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.UserLoginDTO;
import io.bookbar.bookbarbackend.dto.UserRegistrationDTO;
import io.bookbar.bookbarbackend.dto.UserResponseDTO;
import io.bookbar.bookbarbackend.entities.User;
import io.bookbar.bookbarbackend.mapper.UserMapper;
import io.bookbar.bookbarbackend.service.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthServiceImpl authService;


    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO) {
        try {
            User registeredUser = authService.register(userRegistrationDTO);
            UserResponseDTO responseDTO = UserMapper.toUserResponseDTO(registeredUser);
            return ResponseEntity.ok(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        try {
            Map<String, String> tokens = authService.authenticate(userLoginDTO);
            return ResponseEntity.ok(tokens);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Map<String, String>> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refresh_token");
        try {
            String token = authService.refreshToken(refreshToken);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}
