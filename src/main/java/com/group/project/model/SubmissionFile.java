package com.group.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "submission_files")
public class SubmissionFile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "file_name", nullable = false, length = 255)
    private String fileName;
    
    @Column(name = "file_size")
    private Long fileSize;
    
    @Column(name = "file_type", length = 100)
    private String fileType;
    
    @Column(name = "file_data", columnDefinition = "LONGTEXT")
    private String fileData;
    
    @ManyToOne
    @JoinColumn(name = "submission_id")
    private Submission submission;
    
    // Constructors
    public SubmissionFile() {}
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public Long getFileSize() {
        return fileSize;
    }
    
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    
    public String getFileType() {
        return fileType;
    }
    
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    
    public String getFileData() {
        return fileData;
    }
    
    public void setFileData(String fileData) {
        this.fileData = fileData;
    }
    
    public Submission getSubmission() {
        return submission;
    }
    
    public void setSubmission(Submission submission) {
        this.submission = submission;
    }
}