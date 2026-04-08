package com.group.project.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_messages")
public class ChatMessage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    
    @Column(name = "sent_at")
    private LocalDateTime sentAt;
    
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    
    public ChatMessage() {
        this.sentAt = LocalDateTime.now();
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
    
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
    
    public User getSender() { return sender; }
    public void setSender(User sender) { this.sender = sender; }
}