package com.track.tracker.controller;

import com.track.tracker.model.Member;
import com.track.tracker.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveMember(@RequestBody Member member) {
        String message = memberService.saveMember(member);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Member>> getAllMembers() {
        return new ResponseEntity<>(memberService.getAllMembers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable UUID id) {
        Optional<Member> member = memberService.getMemberById(id);
        if (member.isPresent()) {
            return new ResponseEntity<>(member.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The member with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateMember(@PathVariable UUID id, @RequestBody Member member) {
        String message = memberService.updateMember(id, member);
        if (message.equals("Member updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable UUID id) {
        String message = memberService.deleteMember(id);
        if (message.equals("Member deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    // C1
    @GetMapping("/by-skill")
    public ResponseEntity<List<Member>> getMembersBySkill(@RequestParam String name) {
        return new ResponseEntity<>(memberService.getMembersBySkill(name), HttpStatus.OK);
    }

    // D3
    @PutMapping("/{memberId}/skills/{skillId}")
    public ResponseEntity<String> addSkillToMember(@PathVariable UUID memberId, @PathVariable UUID skillId) {
        String message = memberService.addSkillToMember(memberId, skillId);
        if (message.equals("Skill added to the member")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else if (message.equals("The member already has that skill")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    // D4
    @PatchMapping("/{memberId}/workstation")
    public ResponseEntity<String> changeWorkstation(@PathVariable UUID memberId, @RequestParam int stationNumber) {
        String message = memberService.changeWorkstation(memberId, stationNumber);
        if (message.equals("Workstation changed successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else if (message.equals("That workstation is taken by another member")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}