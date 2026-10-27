package com.track.tracker.repository;

import com.track.tracker.model.Task;
import com.track.tracker.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    // B2
    List<Task> findByStatusOrderByDueDateAsc(TaskStatus status);
}