package com.group.project.controller;

import com.group.project.dto.GroupDTO;
import com.group.project.model.Group;
import com.group.project.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/groups")
@CrossOrigin(origins = "*")
public class GroupController {
    
    @Autowired
    private GroupService groupService;
    
    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody GroupDTO groupDTO) {
        Group group = groupService.createGroup(groupDTO);
        return ResponseEntity.ok(group);
    }
    
    @GetMapping
    public ResponseEntity<List<Group>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable Long id) {
        return groupService.getGroupById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<Group> updateGroupStatus(@PathVariable Long id, @RequestParam String status) {
        Group.GroupStatus groupStatus = Group.GroupStatus.valueOf(status);
        Group group = groupService.updateGroupStatus(id, groupStatus);
        return group != null ? ResponseEntity.ok(group) : ResponseEntity.notFound().build();
    }
}