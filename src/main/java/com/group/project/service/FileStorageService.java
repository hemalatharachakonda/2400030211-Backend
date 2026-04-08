package com.group.project.service;

import com.group.project.model.SharedFile;
import com.group.project.model.Project;
import com.group.project.model.User;
import com.group.project.model.SubmissionFile;
import com.group.project.repository.SharedFileRepository;
import com.group.project.repository.ProjectRepository;
import com.group.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class FileStorageService {
    
    @Autowired
    private SharedFileRepository sharedFileRepository;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public SharedFile uploadSharedFile(Long projectId, Long userId, MultipartFile file) throws IOException {
        Optional<Project> projectOpt = projectRepository.findById(projectId);
        Optional<User> userOpt = userRepository.findById(userId);
        
        if (projectOpt.isPresent() && userOpt.isPresent()) {
            String base64Data = Base64.getEncoder().encodeToString(file.getBytes());
            String dataUrl = "data:" + file.getContentType() + ";base64," + base64Data;
            
            SharedFile sharedFile = new SharedFile();
            sharedFile.setFileName(file.getOriginalFilename());
            sharedFile.setFileSize(file.getSize());
            sharedFile.setFileType(file.getContentType());
            sharedFile.setFileData(dataUrl);
            sharedFile.setProject(projectOpt.get());
            sharedFile.setUploadedBy(userOpt.get());
            
            return sharedFileRepository.save(sharedFile);
        }
        return null;
    }
    
    public List<SharedFile> getSharedFiles(Long projectId) {
        return sharedFileRepository.findByProjectIdOrderByUploadedAtDesc(projectId);
    }
    
    @Transactional
    public void deleteSharedFile(Long fileId, Long userId) {
        Optional<SharedFile> fileOpt = sharedFileRepository.findById(fileId);
        if (fileOpt.isPresent() && fileOpt.get().getUploadedBy().getId().equals(userId)) {
            sharedFileRepository.deleteById(fileId);
        }
    }
    
    public SubmissionFile createSubmissionFile(MultipartFile file) throws IOException {
        String base64Data = Base64.getEncoder().encodeToString(file.getBytes());
        String dataUrl = "data:" + file.getContentType() + ";base64," + base64Data;
        
        SubmissionFile submissionFile = new SubmissionFile();
        submissionFile.setFileName(file.getOriginalFilename());
        submissionFile.setFileSize(file.getSize());
        submissionFile.setFileType(file.getContentType());
        submissionFile.setFileData(dataUrl);
        
        return submissionFile;
    }
}