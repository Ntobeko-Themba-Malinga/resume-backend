package com.resume.resume.service.impl;

import com.resume.resume.model.Project;
import com.resume.resume.repository.ProjectRepository;
import com.resume.resume.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public List<Project> retrieveAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(long id, Project updatedProject) {
        return null;
    }
}
