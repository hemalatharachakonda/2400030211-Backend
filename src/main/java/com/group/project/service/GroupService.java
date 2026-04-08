package com.group.project.service;

import com.group.project.dto.GroupDTO;
import com.group.project.model.Group;
import com.group.project.model.Project;
import com.group.project.repository.GroupRepository;
import com.group.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    
    @Autowired
    private GroupRepository groupRepository;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Transactional
    public Group createGroup(GroupDTO groupDTO) {
        Group group = new Group();
        group.setName(groupDTO.getName());
        group = groupRepository.save(group);
        
        Project project = new Project();
        project.setTitle(groupDTO.getProjectTitle());
        project.setDescription(groupDTO.getProjectDescription());
        project.setDeadline(groupDTO.getProjectDeadline());
        project.setRequirements(groupDTO.getRequirements());
        project.setGroup(group);
        projectRepository.save(project);
        
        return group;
    }
    
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }
    
    public Optional<Group> getGroupById(Long id) {
        return groupRepository.findById(id);
    }
    
    @Transactional
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
    
    @Transactional
    public Group updateGroupStatus(Long id, Group.GroupStatus status) {
        Optional<Group> groupOpt = groupRepository.findById(id);
        if (groupOpt.isPresent()) {
            Group group = groupOpt.get();
            group.setStatus(status);
            return groupRepository.save(group);
        }
        return null;
    }
}