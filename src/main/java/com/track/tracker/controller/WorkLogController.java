package com.track.tracker.controller;

import com.track.tracker.model.WorkLog;
import com.track.tracker.service.WorkLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/work-logs")
public class WorkLogController {

    private final WorkLogService workLogService;

    public WorkLogController(WorkLogService workLogService) {
        this.workLogService = workLogService;
    }

    // E1
    @PostMapping("/save")
    public ResponseEntity<String> saveWorkLog(@RequestParam UUID taskId, @RequestBody WorkLog workLog) {
        String message = workLogService.saveWorkLog(taskId, workLog);
        if (message.equals("Work log saved successfully")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else if (message.equals("Work logs are only for DONE tasks")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    // E2
    @GetMapping("/project/{code}")
    public ResponseEntity<List<WorkLog>> getWorkLogsOfProject(@PathVariable String code) {
        return new ResponseEntity<>(workLogService.getWorkLogsOfProject(code), HttpStatus.OK);
    }
}