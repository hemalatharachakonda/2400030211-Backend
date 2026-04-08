package com.group.project.controller;

import com.group.project.dto.LoginRequest;
import com.group.project.dto.LoginResponse;
import com.group.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
    }
}