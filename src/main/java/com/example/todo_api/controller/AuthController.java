package com.example.todo_api.controller;


import com.example.todo_api.dto.UserRegisterRequest;
import com.example.todo_api.dto.UserResponse;
import com.example.todo_api.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody UserRegisterRequest registerRequest){
        return authService.register(registerRequest);
    }

//  Client stores the token obtain in response
//  (e.g., in memory, localStorage, or secure storage in mobile).
//  It will attach this on every protected request.
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String username, @RequestParam String password){

        String token = authService.login(username, password);
        Map<String, String> response = new HashMap<>();

        response.put("token", token);
        response.put("username", username);
        return ResponseEntity.ok(response);
    }
}
