package com.group.project.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "grades")
public class Grade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "grade_value")
    private Integer gradeValue;
    
    @Column(columnDefinition = "TEXT")
    private String feedback;
    
    @Column(name = "graded_at")
    private LocalDateTime gradedAt;
    
    @OneToOne
    @JoinColumn(name = "submission_id")
    private Submission submission;
    
    @ManyToOne
    @JoinColumn(name = "graded_by_id")
    private User gradedBy;
    
    // Constructors
    public Grade() {
        this.gradedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getGradeValue() {
        return gradeValue;
    }
    
    public void setGradeValue(Integer gradeValue) {
        this.gradeValue = gradeValue;
    }
    
    public String getFeedback() {
        return feedback;
    }
    
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    
    public LocalDateTime getGradedAt() {
        return gradedAt;
    }
    
    public void setGradedAt(LocalDateTime gradedAt) {
        this.gradedAt = gradedAt;
    }
    
    public Submission getSubmission() {
        return submission;
    }
    
    public void setSubmission(Submission submission) {
        this.submission = submission;
    }
    
    public User getGradedBy() {
        return gradedBy;
    }
    
    public void setGradedBy(User gradedBy) {
        this.gradedBy = gradedBy;
    }
}