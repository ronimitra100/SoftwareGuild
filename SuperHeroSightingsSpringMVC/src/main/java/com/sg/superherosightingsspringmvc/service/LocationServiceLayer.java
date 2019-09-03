package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.model.*;
import com.sg.superherosightingsspringmvc.dao.*;
import java.util.List;

public interface LocationServiceLayer {
    public void addLocation(Location location);
    public Location getLocationById(int locationId);
    public void updateLocation(Location location);
    public void deleteLocationById(int locationId);
    public List<Location> getAllLocations();
    public List<Location> getLocationsVisitedBySuperhero(int superheroId);  
}
