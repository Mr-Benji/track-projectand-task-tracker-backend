package com.track.tracker.repository;

import com.track.tracker.model.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface WorkLogRepository extends JpaRepository<WorkLog, UUID> {

    // E2
    @Query("SELECT w FROM WorkLog w WHERE w.task.project.code = :code ORDER BY w.loggedDate DESC")
    List<WorkLog> findWorkLogsOfProject(@Param("code") String code);
}