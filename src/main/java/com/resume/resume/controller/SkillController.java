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
    public Skill addingSkill(@RequestBody Skill skill) {
        return skillService.addSkill(skill);
    }

    @PutMapping(path = "{skillId}")
    public Skill updatingSkill(@PathVariable("skillId") long skillId,
                               @RequestBody Skill updatedSkill) {
        return skillService.updateSkill(skillId, updatedSkill);
    }

    @DeleteMapping(path = "{skillId}")
    public void deletingSkill(@PathVariable("skillId") long skillId) {
        skillService.deleteSkill(skillId);
    }
}
