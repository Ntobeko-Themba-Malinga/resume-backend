package com.resume.resume.service.impl;

import com.resume.resume.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

    @Mock private ProjectRepository projectRepository;
    private ProjectServiceImpl underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new ProjectServiceImpl(projectRepository);
    }

    @Test
    void retrieveAllProjects() {

        underTest.retrieveAllProjects();

        verify(projectRepository).findAll();
    }
}