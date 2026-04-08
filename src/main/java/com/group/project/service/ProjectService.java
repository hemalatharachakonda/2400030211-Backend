package com.group.project.service;

import com.group.project.dto.GradeDTO;
import com.group.project.model.*;
import com.group.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private ProgressReportRepository progressReportRepository;
    
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    
    @Autowired
    private SubmissionRepository submissionRepository;
    
    @Autowired
    private GradeRepository gradeRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public Optional<Project> getProjectByGroupId(Long groupId) {
        List<Project> projects = projectRepository.findByGroupId(groupId);
        return projects.isEmpty() ? Optional.empty() : Optional.of(projects.get(0));
    }
    
    @Transactional
    public ProgressReport addProgressReport(Long projectId, Long userId, String content) {
        Optional<Project> projectOpt = projectRepository.findById(projectId);
        Optional<User> userOpt = userRepository.findById(userId);
        
        if (projectOpt.isPresent() && userOpt.isPresent()) {
            ProgressReport report = new ProgressReport();
            report.setContent(content);
            report.setProject(projectOpt.get());
            report.setUser(userOpt.get());
            return progressReportRepository.save(report);
        }
        return null;
    }
    
    public List<ProgressReport> getProgressReports(Long projectId) {
        return progressReportRepository.findByProjectIdOrderBySubmittedAtDesc(projectId);
    }
    
    @Transactional
    public ChatMessage addChatMessage(Long projectId, Long userId, String content) {
        Optional<Project> projectOpt = projectRepository.findById(projectId);
        Optional<User> userOpt = userRepository.findById(userId);
        
        if (projectOpt.isPresent() && userOpt.isPresent()) {
            ChatMessage message = new ChatMessage();
            message.setContent(content);
            message.setProject(projectOpt.get());
            message.setSender(userOpt.get());
            return chatMessageRepository.save(message);
        }
        return null;
    }
    
    public List<ChatMessage> getChatMessages(Long projectId) {
        return chatMessageRepository.findByProjectIdOrderBySentAtAsc(projectId);
    }
    
    public List<Submission> getSubmissionsByProject(Long projectId) {
        return submissionRepository.findByProjectId(projectId);
    }
    
    @Transactional
    public Submission submitProject(Long projectId, Long userId, List<SubmissionFile> files) {
        Optional<Project> projectOpt = projectRepository.findById(projectId);
        Optional<User> userOpt = userRepository.findById(userId);
        
        if (projectOpt.isPresent() && userOpt.isPresent()) {
            Submission submission = new Submission();
            submission.setProject(projectOpt.get());
            submission.setUser(userOpt.get());
            submission.setStatus("pending");
            submission = submissionRepository.save(submission);
            
            for (SubmissionFile file : files) {
                file.setSubmission(submission);
            }
            
            return submission;
        }
        return null;
    }
    
    @Transactional
    public Grade addGrade(Long submissionId, Long teacherId, GradeDTO gradeDTO) {
        Optional<Submission> submissionOpt = submissionRepository.findById(submissionId);
        Optional<User> teacherOpt = userRepository.findById(teacherId);
        
        if (submissionOpt.isPresent() && teacherOpt.isPresent()) {
            Submission submission = submissionOpt.get();
            submission.setStatus("graded");
            submissionRepository.save(submission);
            
            Grade grade = new Grade();
            grade.setGradeValue(gradeDTO.getGradeValue());
            grade.setFeedback(gradeDTO.getFeedback());
            grade.setSubmission(submission);
            grade.setGradedBy(teacherOpt.get());
            
            return gradeRepository.save(grade);
        }
        return null;
    }
}