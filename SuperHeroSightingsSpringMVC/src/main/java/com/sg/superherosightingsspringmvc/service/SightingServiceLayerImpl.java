package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.model.*;
import com.sg.superherosightingsspringmvc.dao.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SightingServiceLayerImpl implements SightingServiceLayer{
    SightingDao sightingDao;
    
    @Inject
    public SightingServiceLayerImpl(SightingDao sightingDao){
       this.sightingDao = sightingDao; 
    }

    @Override
    public void addSighting(Sighting sighting) {
        sightingDao.addSighting(sighting);
    }

    @Override
    public Sighting getSightingById(int sightingId) {
        return sightingDao.getSightingById(sightingId);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        sightingDao.updateSighting(sighting);
    }

    @Override
    public void deleteSighting(int sightingId) {
        sightingDao.deleteSighting(sightingId);
    }

    @Override
    public List<Sighting> getAllSightings() {
        return sightingDao.getAllSightings();
    }

    @Override
    public List<Sighting> getSightingsByDate(LocalDate date) {
        return sightingDao.getSightingsByDate(date);
    }
    
     @Override
    public List<Sighting> getRecentSightings() {
        List<Sighting> listOfAllSightings = sightingDao.getAllSightings();
        List<Sighting> sortedListOfUptoTenRecentSightings = listOfAllSightings.stream().sorted(Comparator.comparing(Sighting::getSightingTime, Comparator.nullsLast(Comparator.reverseOrder()))).limit(10).collect(Collectors.toList());
        return sortedListOfUptoTenRecentSightings;
    }

}
