package com.example.todo_api.service;

import com.example.todo_api.dto.UserRegisterRequest;
import com.example.todo_api.dto.UserResponse;
import com.example.todo_api.model.User;
import com.example.todo_api.repository.UserRepository;
import com.example.todo_api.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    // Registers user for first time. (No JWT here.)
    public UserResponse register(UserRegisterRequest request) {

        // Check if user already exists
        if (userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new RuntimeException("Email already exists");
        }

        // Create new user with hashed password
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        User saved = userRepository.save(user);
        return toResponse(saved);
    }


    //  First time login then token generated to use by client later for requests.
//    POST /api/auth/login?username=X&password=Y → get JWT token.
    public String login(String username, String password){
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(password, user.getPasswordHash())){
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(username);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

    // Mapper : User -> UserResponse
    private UserResponse toResponse(User user) {
        UserResponse res = new UserResponse();
        res.setId(user.getId());
        res.setUsername(user.getUsername());
        res.setEmail(user.getEmail());
        return res;
    }
}