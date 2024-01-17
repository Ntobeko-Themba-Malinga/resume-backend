package com.resume.resume.controller;

import com.resume.resume.model.Project;
import com.resume.resume.service.ProjectService;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/projects")
@Data
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public List<Project> retrievingAllProjects() {
        return projectService.retrieveAllProjects();
    }
}
