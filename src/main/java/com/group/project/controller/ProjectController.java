package com.group.project.controller;

import com.group.project.dto.GradeDTO;
import com.group.project.dto.MessageDTO;
import com.group.project.model.*;
import com.group.project.service.ProjectService;
import com.group.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/group/{groupId}")
    public ResponseEntity<Project> getProjectByGroupId(@PathVariable Long groupId) {
        return projectService.getProjectByGroupId(groupId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/{projectId}/reports")
    public ResponseEntity<ProgressReport> addProgressReport(
            @PathVariable Long projectId,
            @RequestParam String username,
            @RequestBody MessageDTO message) {
        
        return userService.findByUsername(username)
            .map(user -> ResponseEntity.ok(projectService.addProgressReport(projectId, user.getId(), message.getContent())))
            .orElse(ResponseEntity.badRequest().build());
    }
    
    @GetMapping("/{projectId}/reports")
    public ResponseEntity<List<ProgressReport>> getProgressReports(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.getProgressReports(projectId));
    }
    
    @PostMapping("/{projectId}/chat")
    public ResponseEntity<ChatMessage> addChatMessage(
            @PathVariable Long projectId,
            @RequestParam String username,
            @RequestBody MessageDTO message) {
        
        return userService.findByUsername(username)
            .map(user -> ResponseEntity.ok(projectService.addChatMessage(projectId, user.getId(), message.getContent())))
            .orElse(ResponseEntity.badRequest().build());
    }
    
    @GetMapping("/{projectId}/chat")
    public ResponseEntity<List<ChatMessage>> getChatMessages(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.getChatMessages(projectId));
    }
    
    @GetMapping("/{projectId}/submissions")
    public ResponseEntity<List<Submission>> getSubmissions(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.getSubmissionsByProject(projectId));
    }
    
    @PostMapping("/submissions/{submissionId}/grade")
    public ResponseEntity<Grade> addGrade(
            @PathVariable Long submissionId,
            @RequestParam String teacherUsername,
            @RequestBody GradeDTO gradeDTO) {
        
        return userService.findByUsername(teacherUsername)
            .filter(user -> user.getRole() == User.Role.teacher)
            .map(user -> ResponseEntity.ok(projectService.addGrade(submissionId, user.getId(), gradeDTO)))
            .orElse(ResponseEntity.badRequest().build());
    }
}