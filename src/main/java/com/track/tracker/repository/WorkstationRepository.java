package com.track.tracker.repository;

import com.track.tracker.model.Workstation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkstationRepository extends JpaRepository<Workstation, UUID> {
}