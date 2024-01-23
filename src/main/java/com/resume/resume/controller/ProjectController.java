package com.resume.resume.controller;

import com.resume.resume.model.Project;
import com.resume.resume.service.ProjectService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/projects")
@Data
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public List<Project> retrievingAllProjects() {
        return projectService.retrieveAllProjects();
    }

    @PostMapping
    public Project addingAProject(@RequestBody Project project) {
        return projectService.addProject(project);
    }

    @PutMapping(path = "{projectId}")
    public Project updatingProject(@PathVariable("projectId") long projectId,
                                   @RequestBody Project updatedProject) {
        return projectService.updateProject(projectId, updatedProject);
    }

    @DeleteMapping(path = "{projectId}")
    public void deletingProject(@PathVariable("projectId") long projectId) {
        projectService.deleteProject(projectId);
    }
}

