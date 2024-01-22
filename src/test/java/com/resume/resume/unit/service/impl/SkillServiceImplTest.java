package com.resume.resume.unit.service.impl;

import com.resume.resume.exception.SkillNotFoundException;
import com.resume.resume.model.Skill;
import com.resume.resume.repository.SkillRepository;
import com.resume.resume.service.impl.SkillServiceImpl;
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

    @Test
    void updateSkillIfSkillExist() {
        // Given
        long skillId = 1;
        Skill skill = new Skill(
          "testSkill",
          3
        );

        String updateSkillTitle = "updated testSkill";
        int updateSkillLevel = 4;
        Skill updateSkill = new Skill(
                updateSkillTitle,
                updateSkillLevel
        );

        when(skillRepository.findById(skillId))
                .thenReturn(Optional.of(skill));

        // act
        underTest.updateSkill(skillId, updateSkill);

        // then
        assertThat(skill.getTitle()).isEqualTo(updateSkillTitle);
        assertThat(skill.getLevel()).isEqualTo(updateSkillLevel);
    }

    @Test
    void updateSkillIfSkillDoesNotExist() {
        // Given
        long skillId = 1;

        String updateSkillTitle = "updated testSkill";
        int updateSkillLevel = 4;
        Skill updateSkill = new Skill(
                updateSkillTitle,
                updateSkillLevel
        );

        // act
        // then
        assertThatThrownBy(() ->  underTest.updateSkill(skillId, updateSkill))
                .isInstanceOf(SkillNotFoundException.class)
                .hasMessage("Skill with id " + skillId + " not found!");
    }
}