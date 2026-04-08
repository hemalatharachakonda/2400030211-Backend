package com.group.project.controller;

import com.group.project.model.User;
import com.group.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{username}/profile")
    public ResponseEntity<?> updateProfile(
            @PathVariable String username,
            @RequestBody User updatedUser) {
        
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (updatedUser.getFullName() != null) {
                user.setFullName(updatedUser.getFullName());
            }
            if (updatedUser.getEmail() != null) {
                user.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getDepartment() != null) {
                user.setDepartment(updatedUser.getDepartment());
            }
            return ResponseEntity.ok(userRepository.save(user));
        }
        return ResponseEntity.notFound().build();
    }
}