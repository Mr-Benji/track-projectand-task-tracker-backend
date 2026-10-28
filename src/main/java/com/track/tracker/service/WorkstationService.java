package com.track.tracker.service;

import com.track.tracker.model.Workstation;
import com.track.tracker.repository.WorkstationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkstationService {

    private final WorkstationRepository workstationRepository;

    public WorkstationService(WorkstationRepository workstationRepository) {
        this.workstationRepository = workstationRepository;
    }

    public String saveWorkstation(Workstation workstation) {
        workstationRepository.save(workstation);
        return "Workstation saved successfully";
    }

    public List<Workstation> getAllWorkstations() {
        return workstationRepository.findAll();
    }

    public Optional<Workstation> getWorkstationById(UUID id) {
        return workstationRepository.findById(id);
    }

    public String updateWorkstation(UUID id, Workstation newWorkstation) {
        Optional<Workstation> optionalWorkstation = workstationRepository.findById(id);
        if (optionalWorkstation.isEmpty()) {
            return "The workstation with that id does not exist";
        }
        Workstation workstation = optionalWorkstation.get();
        workstation.setStationNumber(newWorkstation.getStationNumber());
        workstation.setName(newWorkstation.getName());
        workstationRepository.save(workstation);
        return "Workstation updated successfully";
    }

    public String deleteWorkstation(UUID id) {
        if (!workstationRepository.existsById(id)) {
            return "The workstation with that id does not exist";
        }
        workstationRepository.deleteById(id);
        return "Workstation deleted successfully";
    }

    // C2
    public List<Workstation> getFreeWorkstations() {
        return workstationRepository.findFreeWorkstations();
    }
}