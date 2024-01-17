package com.resume.resume.service.impl;

import com.resume.resume.model.Skill;
import com.resume.resume.repository.SkillRepository;
import com.resume.resume.service.SkillService;
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
}
