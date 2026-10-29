package com.track.tracker.service;

import com.track.tracker.model.Member;
import com.track.tracker.model.Skill;
import com.track.tracker.model.Workstation;
import com.track.tracker.repository.MemberRepository;
import com.track.tracker.repository.SkillRepository;
import com.track.tracker.repository.WorkstationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final SkillRepository skillRepository;
    private final WorkstationRepository workstationRepository;

    public MemberService(MemberRepository memberRepository,
                         SkillRepository skillRepository,
                         WorkstationRepository workstationRepository) {
        this.memberRepository = memberRepository;
        this.skillRepository = skillRepository;
        this.workstationRepository = workstationRepository;
    }

    public String saveMember(Member member) {
        memberRepository.save(member);
        return "Member saved successfully";
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(UUID id) {
        return memberRepository.findById(id);
    }

    // skills and workstation are changed in D3 and D4
    public String updateMember(UUID id, Member newMember) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isEmpty()) {
            return "The member with that id does not exist";
        }
        Member member = optionalMember.get();
        member.setFirstName(newMember.getFirstName());
        member.setLastName(newMember.getLastName());
        member.setEmail(newMember.getEmail());
        member.setRole(newMember.getRole());
        memberRepository.save(member);
        return "Member updated successfully";
    }

    public String deleteMember(UUID id) {
        if (!memberRepository.existsById(id)) {
            return "The member with that id does not exist";
        }
        memberRepository.deleteById(id);
        return "Member deleted successfully";
    }

    // C1
    public List<Member> getMembersBySkill(String name) {
        return memberRepository.findMembersBySkillName(name);
    }

    // D3
    @Transactional
    public String addSkillToMember(UUID memberId, UUID skillId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isEmpty()) {
            return "The member with that id does not exist";
        }
        Optional<Skill> optionalSkill = skillRepository.findById(skillId);
        if (optionalSkill.isEmpty()) {
            return "That skill does not exist";
        }
        Member member = optionalMember.get();
        Skill skill = optionalSkill.get();
        if (member.getSkills().contains(skill)) {
            return "The member already has that skill";
        }
        member.getSkills().add(skill);
        memberRepository.save(member);
        return "Skill added to the member";
    }

    // D4
    public String changeWorkstation(UUID memberId, int stationNumber) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isEmpty()) {
            return "The member with that id does not exist";
        }
        Optional<Workstation> optionalWorkstation = workstationRepository.findByStationNumber(stationNumber);
        if (optionalWorkstation.isEmpty()) {
            return "The workstation with that stationNumber does not exist";
        }
        Workstation workstation = optionalWorkstation.get();
        if (memberRepository.existsByWorkstation(workstation)) {
            return "That workstation is taken by another member";
        }
        Member member = optionalMember.get();
        member.setWorkstation(workstation);
        memberRepository.save(member);
        return "Workstation changed successfully";
    }
}