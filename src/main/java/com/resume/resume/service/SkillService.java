package com.resume.resume.service;

import com.resume.resume.model.Skill;

import java.util.List;

public interface SkillService {

    public List<Skill> retrieveAllSkills();

    public Skill addSkill(Skill skill);

    public Skill updateSkill(long id, Skill updatedSkill);
}
