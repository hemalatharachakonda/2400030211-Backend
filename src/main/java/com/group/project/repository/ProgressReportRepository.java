package com.group.project.repository;

import com.group.project.model.ProgressReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProgressReportRepository extends JpaRepository<ProgressReport, Long> {
    List<ProgressReport> findByProjectIdOrderBySubmittedAtDesc(Long projectId);
}