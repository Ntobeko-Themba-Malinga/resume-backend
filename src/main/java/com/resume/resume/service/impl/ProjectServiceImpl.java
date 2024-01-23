package com.resume.resume.service.impl;

import com.resume.resume.exception.ProjectNotFoundException;
import com.resume.resume.model.Project;
import com.resume.resume.repository.ProjectRepository;
import com.resume.resume.service.ProjectService;
import jakarta.transaction.Transactional;
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

    @Transactional
    @Override
    public Project updateProject(long id, Project updatedProject) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(
                        "Project with id " + id + " not found!"
                ));

        if (!updatedProject.getTitle().trim().isEmpty())
            project.setTitle(updatedProject.getTitle());

        if (!updatedProject.getUrl().trim().isEmpty())
            project.setUrl(updatedProject.getUrl());

        if (!updatedProject.getImage().trim().isEmpty())
            project.setImage(updatedProject.getImage());
        return project;
    }
}
