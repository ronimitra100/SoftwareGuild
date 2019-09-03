package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.model.*;
import com.sg.superherosightingsspringmvc.dao.*;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;


public class LocationServiceLayerImpl implements LocationServiceLayer{
    LocationDao locationDao;
    
    @Inject
    public LocationServiceLayerImpl(LocationDao locationDao){
        this.locationDao = locationDao;
    }

    @Override
    public void addLocation(Location location) {
        locationDao.addLocation(location);
    }

    @Override
    public Location getLocationById(int locationId) {
        return locationDao.getLocationById(locationId);
    }

    @Override
    public void updateLocation(Location location) {
        locationDao.updateLocation(location);
    }
    
    @Override
    public void deleteLocationById(int locationId) {
        locationDao.deleteLocationById(locationId);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationDao.getAllLocations();
    }

    @Override
    public List<Location> getLocationsVisitedBySuperhero(int superheroId) {
        return locationDao.getLocationsVisitedBySuperhero(superheroId);
    }

}
