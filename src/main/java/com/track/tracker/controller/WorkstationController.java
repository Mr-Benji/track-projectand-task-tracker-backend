package com.track.tracker.controller;

import com.track.tracker.model.Workstation;
import com.track.tracker.service.WorkstationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/workstations")
public class WorkstationController {

    private final WorkstationService workstationService;

    public WorkstationController(WorkstationService workstationService) {
        this.workstationService = workstationService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveWorkstation(@RequestBody Workstation workstation) {
        String message = workstationService.saveWorkstation(workstation);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Workstation>> getAllWorkstations() {
        return new ResponseEntity<>(workstationService.getAllWorkstations(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWorkstationById(@PathVariable UUID id) {
        Optional<Workstation> workstation = workstationService.getWorkstationById(id);
        if (workstation.isPresent()) {
            return new ResponseEntity<>(workstation.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The workstation with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateWorkstation(@PathVariable UUID id, @RequestBody Workstation workstation) {
        String message = workstationService.updateWorkstation(id, workstation);
        if (message.equals("Workstation updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWorkstation(@PathVariable UUID id) {
        String message = workstationService.deleteWorkstation(id);
        if (message.equals("Workstation deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    // C2
    @GetMapping("/free")
    public ResponseEntity<List<Workstation>> getFreeWorkstations() {
        return new ResponseEntity<>(workstationService.getFreeWorkstations(), HttpStatus.OK);
    }
}