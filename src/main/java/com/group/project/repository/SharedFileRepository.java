package com.group.project.repository;

import com.group.project.model.SharedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SharedFileRepository extends JpaRepository<SharedFile, Long> {
    List<SharedFile> findByProjectIdOrderByUploadedAtDesc(Long projectId);
}