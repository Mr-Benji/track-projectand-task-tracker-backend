package com.track.tracker.repository;

import com.track.tracker.model.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkLogRepository extends JpaRepository<WorkLog, UUID> {
}