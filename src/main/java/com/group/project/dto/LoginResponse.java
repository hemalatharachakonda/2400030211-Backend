package com.group.project.dto;

public class LoginResponse {
    private boolean success;
    private String role;
    private String fullName;
    private String username;
    private String message;
    
    public LoginResponse() {}
    
    public LoginResponse(boolean success, String role, String fullName, String username, String message) {
        this.success = success;
        this.role = role;
        this.fullName = fullName;
        this.username = username;
        this.message = message;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}