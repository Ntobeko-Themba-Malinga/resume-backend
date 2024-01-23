package com.resume.resume.unit.service.impl;

import com.resume.resume.exception.ProjectNotFoundException;
import com.resume.resume.model.Project;
import com.resume.resume.repository.ProjectRepository;
import com.resume.resume.service.impl.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    void addProject() {
        // given
        Project project = new Project(
                "test",
                "test.com/test.git",
                "test.com/test.jpg"
        );

        // act
        underTest.addProject(project);

        // then
        ArgumentCaptor<Project> projectArgumentCaptor = ArgumentCaptor
                .forClass(Project.class);
        verify(projectRepository).save(projectArgumentCaptor.capture());

        assertThat(projectArgumentCaptor.getValue()).isEqualTo(project);
    }

    @Test
    void updateProjectIfExist() {
        // given
        long projectId = 1;
        Project project = new Project(
                "test",
                "test.com/test.git",
                "test.com/test.jpg"
        );

        Project updatedProject = new Project(
                "updated test",
                "test.com/updated_test.git",
                "test.com/updated_test.jpg"
        );

        when(projectRepository.findById(projectId))
                .thenReturn(Optional.of(project));

        // act
        underTest.updateProject(projectId, updatedProject);

        // then
        assertThat(updatedProject).isEqualTo(project);
    }

    @Test
    void updateProjectIfDoesNotExist() {
        // given
        long projectId = 1;
        Project updatedProject = new Project(
                "updated test",
                "test.com/updated_test.git",
                "test.com/updated_test.jpg"
        );

        // act
        // then
        assertThatThrownBy(() -> underTest.updateProject(projectId, updatedProject))
                .isInstanceOf(ProjectNotFoundException.class)
                .hasMessage("Project with id " + projectId + " not found!");
    }

    @Test
    void deleteProjectIfExist() {
        // given
        long projectId = 1;
        Project project = new Project(
                "test",
                "test.com/test.git",
                "test.com/test.jpg"
        );

        when(projectRepository.findById(projectId))
                .thenReturn(Optional.of(project));

        // act
        underTest.deleteProject(projectId);

        // then
        ArgumentCaptor<Project> projectArgumentCaptor = ArgumentCaptor
                .forClass(Project.class);

        verify(projectRepository).delete(projectArgumentCaptor.capture());

        assertThat(projectArgumentCaptor.getValue()).isEqualTo(project);
    }

    @Test
    void deleteProjectIfDoesNotExist() {
        // given
        long projectId = 1;

        // act
        // then
        assertThatThrownBy(() -> underTest.deleteProject(projectId))
                .isInstanceOf(ProjectNotFoundException.class)
                .hasMessage("Project with id " + projectId + " not found!");
    }
}