package com.track.tracker.repository;

import com.track.tracker.model.Task;
import com.track.tracker.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    // F1
    @Query("SELECT t.status, COUNT(t) FROM Task t GROUP BY t.status")
    List<Object[]> countTasksByStatus();

    // F2
    @Modifying
    @Query("UPDATE Task t SET t.status = com.track.tracker.model.TaskStatus.DROPPED " +
           "WHERE t.member.id = :memberId AND t.dueDate = :date " +
           "AND t.status <> com.track.tracker.model.TaskStatus.DONE")
    int cancelTasksOfMemberOnDate(@Param("memberId") UUID memberId, @Param("date") LocalDate date);
}