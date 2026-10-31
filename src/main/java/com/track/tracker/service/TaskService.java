package com.track.tracker.service;

import com.track.tracker.model.Task;
import com.track.tracker.model.TaskStatus;
import com.track.tracker.repository.TaskRepository;
import com.track.tracker.repository.WorkLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final WorkLogRepository workLogRepository;

    public TaskService(TaskRepository taskRepository, WorkLogRepository workLogRepository) {
        this.taskRepository = taskRepository;
        this.workLogRepository = workLogRepository;
    }

    // D1
    public String saveTask(Task task) {
        if (task.getDueDate().isBefore(LocalDate.now())) {
            return "Task date cannot be in the past";
        }
        if (taskRepository.existsByMemberIdAndDueDateAndStatusNot(
                task.getMember().getId(), task.getDueDate(), TaskStatus.DROPPED)) {
            return "Member already has a task due on that date";
        }
        task.setStatus(TaskStatus.TODO);
        taskRepository.save(task);
        return "Task saved successfully";
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(UUID id) {
        return taskRepository.findById(id);
    }

    // status is only changed in updateTaskStatus (D2)
    public String updateTask(UUID id, Task newTask) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            return "The task with that id does not exist";
        }
        Task task = optionalTask.get();
        task.setTitle(newTask.getTitle());
        task.setPriority(newTask.getPriority());
        task.setDueDate(newTask.getDueDate());
        task.setProject(newTask.getProject());
        task.setMember(newTask.getMember());
        taskRepository.save(task);
        return "Task updated successfully";
    }

    // E3
    public String deleteTask(UUID id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            return "The task with that id does not exist";
        }
        if (workLogRepository.existsByTask(optionalTask.get())) {
            return "That task has work logs, delete them first";
        }
        taskRepository.deleteById(id);
        return "Task deleted successfully";
    }

    // B2
    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatusOrderByDueDateAsc(status);
    }

    // B3
    public List<Task> getTasksBetween(LocalDate start, LocalDate end) {
        return taskRepository.findByDueDateBetweenOrderByDueDateAsc(start, end);
    }

    // D2
    public String updateTaskStatus(UUID id, TaskStatus newStatus) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            return "The task with that id does not exist";
        }
        Task task = optionalTask.get();
        TaskStatus currentStatus = task.getStatus();
        if (!isMoveAllowed(currentStatus, newStatus)) {
            return "Cannot change status from " + currentStatus + " to " + newStatus;
        }
        task.setStatus(newStatus);
        taskRepository.save(task);
        return "Task status updated successfully";
    }

    private boolean isMoveAllowed(TaskStatus current, TaskStatus next) {
        if (current == TaskStatus.TODO) {
            return next == TaskStatus.IN_PROGRESS || next == TaskStatus.DROPPED;
        }
        if (current == TaskStatus.IN_PROGRESS) {
            return next == TaskStatus.DONE || next == TaskStatus.DROPPED;
        }
        return false;
    }

    // F1
    public List<Object[]> getTaskCountByStatus() {
        return taskRepository.countTasksByStatus();
    }
}