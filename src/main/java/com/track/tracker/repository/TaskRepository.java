package com.track.tracker.repository;

import com.track.tracker.model.Task;
import com.track.tracker.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    // B2
    List<Task> findByStatusOrderByDueDateAsc(TaskStatus status);

    // B3
    List<Task> findByDueDateBetweenOrderByDueDateAsc(LocalDate start, LocalDate end);

    // D1
    boolean existsByMemberIdAndDueDateAndStatusNot(UUID memberId, LocalDate dueDate, TaskStatus status);
}