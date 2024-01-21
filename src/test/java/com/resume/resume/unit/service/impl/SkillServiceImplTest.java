package com.resume.resume.unit.service.impl;

import com.resume.resume.model.Skill;
import com.resume.resume.repository.SkillRepository;
import com.resume.resume.service.impl.SkillServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

    @Test
    void addSkill() {
        // given
        Skill skill = new Skill(
                "Test",
                5
        );

        // act
        underTest.addSkill(skill);

        // then
        ArgumentCaptor<Skill> skillArgumentCaptor = ArgumentCaptor
                .forClass(Skill.class);

        verify(skillRepository).save(skillArgumentCaptor.capture());
        assertThat(skillArgumentCaptor.getValue()).isEqualTo(skill);
    }
}