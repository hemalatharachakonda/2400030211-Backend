package com.group.project.service;

import com.group.project.dto.LoginResponse;
import com.group.project.model.User;
import com.group.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public LoginResponse authenticate(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(password)) {
                return new LoginResponse(
                    true,
                    user.getRole().toString(),
                    user.getFullName(),
                    user.getUsername(),
                    "Login successful"
                );
            }
        }
        
        return new LoginResponse(false, null, null, null, "Invalid credentials");
    }
    
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}