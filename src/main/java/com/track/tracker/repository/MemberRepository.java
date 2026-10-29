package com.track.tracker.repository;

import com.track.tracker.model.Member;
import com.track.tracker.model.Workstation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {

    // C1
    @Query("SELECT m FROM Member m JOIN m.skills s WHERE LOWER(s.name) = LOWER(:name)")
    List<Member> findMembersBySkillName(@Param("name") String name);

    // D4
    boolean existsByWorkstation(Workstation workstation);
}