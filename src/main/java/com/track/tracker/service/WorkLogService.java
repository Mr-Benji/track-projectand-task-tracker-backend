package com.track.tracker.service;

import com.track.tracker.model.Task;
import com.track.tracker.model.TaskStatus;
import com.track.tracker.model.WorkLog;
import com.track.tracker.repository.TaskRepository;
import com.track.tracker.repository.WorkLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkLogService {

    private final WorkLogRepository workLogRepository;
    private final TaskRepository taskRepository;

    public WorkLogService(WorkLogRepository workLogRepository, TaskRepository taskRepository) {
        this.workLogRepository = workLogRepository;
        this.taskRepository = taskRepository;
    }

    // E1
    public String saveWorkLog(UUID taskId, WorkLog workLog) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isEmpty()) {
            return "The task with that id does not exist";
        }
        Task task = optionalTask.get();
        if (task.getStatus() != TaskStatus.DONE) {
            return "Work logs are only for DONE tasks";
        }
        workLog.setLoggedDate(LocalDate.now());
        workLog.setTask(task);
        workLogRepository.save(workLog);
        return "Work log saved successfully";
    }

    // E2
    public List<WorkLog> getWorkLogsOfProject(String code) {
        return workLogRepository.findWorkLogsOfProject(code);
    }
}