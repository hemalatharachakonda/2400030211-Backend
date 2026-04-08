package com.group.project.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "submissions")
public class Submission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;
    
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL)
    private List<SubmissionFile> files = new ArrayList<>();
    
    @OneToOne(mappedBy = "submission", cascade = CascadeType.ALL)
    private Grade grade;
    
    public Submission() {
        this.submittedAt = LocalDateTime.now();
        this.status = "pending";
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }
    
    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Project getProject() {
        return project;
    }
    
    public void setProject(Project project) {
        this.project = project;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public List<SubmissionFile> getFiles() {
        return files;
    }
    
    public void setFiles(List<SubmissionFile> files) {
        this.files = files;
    }
    
    public Grade getGrade() {
        return grade;
    }
    
    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}