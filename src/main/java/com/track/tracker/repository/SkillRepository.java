package com.track.tracker.repository;

import com.track.tracker.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SkillRepository extends JpaRepository<Skill, UUID> {

    // C3
    @Query("SELECT s FROM Skill s WHERE s.members IS EMPTY")
    List<Skill> findUnusedSkills();
}