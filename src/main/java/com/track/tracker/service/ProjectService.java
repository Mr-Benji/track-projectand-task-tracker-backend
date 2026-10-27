package com.track.tracker.service;

import com.track.tracker.model.Project;
import com.track.tracker.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public String saveProject(Project project) {
        projectRepository.save(project);
        return "Project saved successfully";
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(UUID id) {
        return projectRepository.findById(id);
    }

    public String updateProject(UUID id, Project newProject) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isEmpty()) {
            return "The project with that id does not exist";
        }
        Project project = optionalProject.get();
        project.setName(newProject.getName());
        project.setCode(newProject.getCode());
        project.setDepartment(newProject.getDepartment());
        project.setLead(newProject.getLead());
        projectRepository.save(project);
        return "Project updated successfully";
    }

    public String deleteProject(UUID id) {
        if (!projectRepository.existsById(id)) {
            return "The project with that id does not exist";
        }
        projectRepository.deleteById(id);
        return "Project deleted successfully";
    }

    // B1
    public List<Project> getProjectsByDepartment(String department) {
        return projectRepository.findByDepartmentIgnoreCaseOrderByNameAsc(department);
    }
}