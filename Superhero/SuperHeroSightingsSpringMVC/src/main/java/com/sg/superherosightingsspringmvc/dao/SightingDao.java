package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.*;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import java.time.*;

public interface SightingDao {
    public void addSighting(Sighting sighting);
    public Sighting getSightingById(int sightingId);
    public void updateSighting(Sighting sighting);
    public void deleteSighting(int sightingId);
    public List<Sighting> getAllSightings();
    public List<Sighting> getSightingsByDate(LocalDate date);
}
