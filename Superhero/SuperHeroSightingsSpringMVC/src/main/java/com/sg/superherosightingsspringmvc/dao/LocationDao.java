package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.*;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import java.time.*;

public interface LocationDao {
    public void addLocation(Location location);
    public Location getLocationById(int locationId);
    public void updateLocation(Location location);
    public void deleteLocationById(int locationId);
    public List<Location> getAllLocations();
    public List<Location> getLocationsVisitedBySuperhero(int superheroId);    
}
