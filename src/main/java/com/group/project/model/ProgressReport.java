package com.group.project.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "progress_reports")
public class ProgressReport {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    
    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;
    
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    public ProgressReport() {
        this.submittedAt = LocalDateTime.now();
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }
    
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}