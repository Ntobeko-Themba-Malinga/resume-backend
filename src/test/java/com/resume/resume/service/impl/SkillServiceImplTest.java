package com.resume.resume.service.impl;

import com.resume.resume.repository.SkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SkillServiceImplTest {

    @Mock private SkillRepository skillRepository;
    private SkillServiceImpl underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new SkillServiceImpl(skillRepository);
    }

    @Test
    void retrieveAllSkills() {

        underTest.retrieveAllSkills();

        verify(skillRepository).findAll();
    }
}