package com.resume.resume.service;

import com.resume.resume.model.Project;

import java.util.List;

public interface ProjectService {

    public List<Project> retrieveAllProjects();

    public Project addProject(Project project);

    public Project updateProject(long id, Project updatedProject);
}
