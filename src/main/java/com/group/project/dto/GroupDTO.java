package com.group.project.dto;

import java.time.LocalDate;
import java.util.List;

public class GroupDTO {
    private String name;
    private List<String> students;
    private String projectTitle;
    private String projectDescription;
    private LocalDate projectDeadline;
    private String requirements;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<String> getStudents() {
        return students;
    }
    
    public void setStudents(List<String> students) {
        this.students = students;
    }
    
    public String getProjectTitle() {
        return projectTitle;
    }
    
    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }
    
    public String getProjectDescription() {
        return projectDescription;
    }
    
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }
    
    public LocalDate getProjectDeadline() {
        return projectDeadline;
    }
    
    public void setProjectDeadline(LocalDate projectDeadline) {
        this.projectDeadline = projectDeadline;
    }
    
    public String getRequirements() {
        return requirements;
    }
    
    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }
}