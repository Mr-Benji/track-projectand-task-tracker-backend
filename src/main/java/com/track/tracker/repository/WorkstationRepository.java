package com.track.tracker.repository;

import com.track.tracker.model.Workstation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkstationRepository extends JpaRepository<Workstation, UUID> {

    // C2
    @Query("SELECT w FROM Workstation w " +
           "WHERE NOT EXISTS (SELECT m FROM Member m WHERE m.workstation = w) " +
           "ORDER BY w.stationNumber ASC")
    List<Workstation> findFreeWorkstations();

    // D4
    Optional<Workstation> findByStationNumber(int stationNumber);
}