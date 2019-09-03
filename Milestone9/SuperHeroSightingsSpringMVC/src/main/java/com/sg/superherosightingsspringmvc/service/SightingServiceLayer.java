package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.model.*;
import com.sg.superherosightingsspringmvc.dao.*;
import java.time.LocalDate;
import java.util.List;

public interface SightingServiceLayer {
    public void addSighting(Sighting sighting);
    public Sighting getSightingById(int sightingId);
    public void updateSighting(Sighting sighting);
    public void deleteSighting(int sightingId);
    public List<Sighting> getAllSightings();
    public List<Sighting> getSightingsByDate(LocalDate date);
    public List<Sighting> getRecentSightings();
}
