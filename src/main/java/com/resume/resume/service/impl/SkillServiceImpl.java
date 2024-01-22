package com.resume.resume.service.impl;

import com.resume.resume.exception.SkillNotFoundException;
import com.resume.resume.model.Skill;
import com.resume.resume.repository.SkillRepository;
import com.resume.resume.service.SkillService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    @Override
    public List<Skill> retrieveAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Skill addSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Transactional
    @Override
    public Skill updateSkill(long id, Skill updatedSkill) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new SkillNotFoundException("Skill with id " + id + " not found!"));
        skill.setTitle(updatedSkill.getTitle());
        skill.setLevel(updatedSkill.getLevel());
        return skill;
    }

    @Override
    public void deleteSkill(long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new SkillNotFoundException("Skill with id " + id + " not found!"));

        skillRepository.delete(skill);
    }
}
