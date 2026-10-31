package com.track.tracker.service;

import com.track.tracker.model.TaskStatus;
import com.track.tracker.repository.MemberRepository;
import com.track.tracker.repository.ProjectRepository;
import com.track.tracker.repository.TaskRepository;
import com.track.tracker.repository.WorkLogRepository;
import com.track.tracker.repository.WorkstationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DashboardService {

    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    private final WorkstationRepository workstationRepository;
    private final TaskRepository taskRepository;
    private final WorkLogRepository workLogRepository;

    public DashboardService(ProjectRepository projectRepository, MemberRepository memberRepository,
                            WorkstationRepository workstationRepository, TaskRepository taskRepository,
                            WorkLogRepository workLogRepository) {
        this.projectRepository = projectRepository;
        this.memberRepository = memberRepository;
        this.workstationRepository = workstationRepository;
        this.taskRepository = taskRepository;
        this.workLogRepository = workLogRepository;
    }

    public Map<String, Long> getSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("projects", projectRepository.count());
        summary.put("members", memberRepository.count());
        summary.put("freeWorkstations", (long) workstationRepository.findFreeWorkstations().size());
        summary.put("tasksDueToday", taskRepository.countByDueDate(LocalDate.now()));
        summary.put("todo", taskRepository.countByStatus(TaskStatus.TODO));
        summary.put("workLogs", workLogRepository.count());
        return summary;
    }
}