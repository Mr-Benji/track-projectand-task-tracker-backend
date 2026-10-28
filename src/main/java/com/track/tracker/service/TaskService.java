package com.track.tracker.service;

import com.track.tracker.model.Task;
import com.track.tracker.model.TaskStatus;
import com.track.tracker.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public String saveTask(Task task) {
        taskRepository.save(task);
        return "Task saved successfully";
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(UUID id) {
        return taskRepository.findById(id);
    }

    public String updateTask(UUID id, Task newTask) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            return "The task with that id does not exist";
        }
        Task task = optionalTask.get();
        task.setTitle(newTask.getTitle());
        task.setPriority(newTask.getPriority());
        task.setDueDate(newTask.getDueDate());
        task.setStatus(newTask.getStatus());
        task.setProject(newTask.getProject());
        task.setMember(newTask.getMember());
        taskRepository.save(task);
        return "Task updated successfully";
    }

    public String deleteTask(UUID id) {
        if (!taskRepository.existsById(id)) {
            return "The task with that id does not exist";
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
}