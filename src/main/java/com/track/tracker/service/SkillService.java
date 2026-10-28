package com.track.tracker.service;

import com.track.tracker.model.Skill;
import com.track.tracker.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public String saveSkill(Skill skill) {
        skillRepository.save(skill);
        return "Skill saved successfully";
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Optional<Skill> getSkillById(UUID id) {
        return skillRepository.findById(id);
    }

    public String updateSkill(UUID id, Skill newSkill) {
        Optional<Skill> optionalSkill = skillRepository.findById(id);
        if (optionalSkill.isEmpty()) {
            return "The skill with that id does not exist";
        }
        Skill skill = optionalSkill.get();
        skill.setName(newSkill.getName());
        skillRepository.save(skill);
        return "Skill updated successfully";
    }

    public String deleteSkill(UUID id) {
        if (!skillRepository.existsById(id)) {
            return "The skill with that id does not exist";
        }
        skillRepository.deleteById(id);
        return "Skill deleted successfully";
    }

    // C3
    public List<Skill> getUnusedSkills() {
        return skillRepository.findUnusedSkills();
    }
}