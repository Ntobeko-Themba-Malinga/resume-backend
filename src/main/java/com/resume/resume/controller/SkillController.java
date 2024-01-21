package com.resume.resume.controller;

import com.resume.resume.model.Skill;
import com.resume.resume.service.SkillService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/skills")
@AllArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @GetMapping
    public List<Skill> retrievingAllSkills() {
        return skillService.retrieveAllSkills();
    }

    @PostMapping
    public void addingSkill(@RequestBody Skill skill) {
        skillService.addSkill(skill);
    }
}
