package com.track.tracker.controller;

import com.track.tracker.model.Project;
import com.track.tracker.service.MemberService;
import com.track.tracker.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final MemberService memberService;

    public ProjectController(ProjectService projectService, MemberService memberService) {
        this.projectService = projectService;
        this.memberService = memberService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveProject(@RequestBody Project project) {
        String message = projectService.saveProject(project);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Project>> getAllProjects() {
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable UUID id) {
        Optional<Project> project = projectService.getProjectById(id);
        if (project.isPresent()) {
            return new ResponseEntity<>(project.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The project with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProject(@PathVariable UUID id, @RequestBody Project project) {
        String message = projectService.updateProject(id, project);
        if (message.equals("Project updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable UUID id) {
        String message = projectService.deleteProject(id);
        if (message.equals("Project deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    // B1
    @GetMapping("/by-department")
    public ResponseEntity<List<Project>> getProjectsByDepartment(@RequestParam String department) {
        return new ResponseEntity<>(projectService.getProjectsByDepartment(department), HttpStatus.OK);
    }

    // C4
    @GetMapping("/of-member/{memberId}")
    public ResponseEntity<?> getProjectsOfMember(@PathVariable UUID memberId) {
        if (memberService.getMemberById(memberId).isEmpty()) {
            return new ResponseEntity<>("The member with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(projectService.getProjectsOfMember(memberId), HttpStatus.OK);
    }
}