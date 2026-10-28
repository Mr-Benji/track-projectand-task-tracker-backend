package com.track.tracker.repository;

import com.track.tracker.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

    // B1
    List<Project> findByDepartmentIgnoreCaseOrderByNameAsc(String department);

    // C4
    @Query("SELECT DISTINCT t.project FROM Task t WHERE t.member.id = :memberId")
    List<Project> findProjectsOfMember(@Param("memberId") UUID memberId);
}