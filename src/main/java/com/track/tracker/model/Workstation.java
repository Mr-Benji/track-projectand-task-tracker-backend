package com.track.tracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "workstation")
public class Workstation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int stationNumber;

    private String name;

}