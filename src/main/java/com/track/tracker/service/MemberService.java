package com.track.tracker.service;

import com.track.tracker.model.Member;
import com.track.tracker.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
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
}