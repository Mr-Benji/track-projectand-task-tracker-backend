package com.track.tracker.repository;

import com.track.tracker.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

    // B1
    List<Project> findByDepartmentIgnoreCaseOrderByNameAsc(String department);
}