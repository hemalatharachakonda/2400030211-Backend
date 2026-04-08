package com.group.project.controller;

import com.group.project.model.SharedFile;
import com.group.project.service.FileStorageService;
import com.group.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "*")
public class FileController {
    
    @Autowired
    private FileStorageService fileStorageService;
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/shared/{projectId}")
    public ResponseEntity<?> uploadSharedFile(
            @PathVariable Long projectId,
            @RequestParam String username,
            @RequestParam MultipartFile file) {
        
        return userService.findByUsername(username)
            .map(user -> {
                try {
                    SharedFile sharedFile = fileStorageService.uploadSharedFile(projectId, user.getId(), file);
                    return ResponseEntity.ok(sharedFile);
                } catch (IOException e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed: " + e.getMessage());
                }
            })
            .orElse(ResponseEntity.badRequest().body("User not found"));
    }
    
    @GetMapping("/shared/{projectId}")
    public ResponseEntity<?> getSharedFiles(@PathVariable Long projectId) {
        try {
            List<SharedFile> files = fileStorageService.getSharedFiles(projectId);
            return ResponseEntity.ok(files);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching files: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/shared/{fileId}")
    public ResponseEntity<?> deleteSharedFile(
            @PathVariable Long fileId,
            @RequestParam String username) {
        
        return userService.findByUsername(username)
            .map(user -> {
                fileStorageService.deleteSharedFile(fileId, user.getId());
                return ResponseEntity.ok("File deleted successfully");
            })
            .orElse(ResponseEntity.badRequest().body("User not found"));
    }
}