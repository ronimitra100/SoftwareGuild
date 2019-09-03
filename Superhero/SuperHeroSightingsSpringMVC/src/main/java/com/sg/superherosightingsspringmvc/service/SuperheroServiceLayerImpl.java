package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.model.*;
import com.sg.superherosightingsspringmvc.dao.*;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import static java.util.Comparator.*;


public class SuperheroServiceLayerImpl implements SuperheroServiceLayer{
    
    SuperheroDao superheroDao;
    
    @Inject
    public SuperheroServiceLayerImpl(SuperheroDao superheroDao){
        this.superheroDao = superheroDao;
    }

    @Override
    public void addSuperhero(Superhero superhero) {
        superheroDao.addSuperhero(superhero);
    }

    @Override
    public Superhero getSuperheroById(int superheroId) {
        return superheroDao.getSuperheroById(superheroId);
    }

    @Override
    public void updateSuperhero(Superhero superhero) {
        superheroDao.updateSuperhero(superhero);
    }

    @Override
    public void deleteSuperheroById(int superheroId) {
        superheroDao.deleteSuperheroById(superheroId);
    }

    @Override
    public List<Superhero> getAllSuperheroes() {
        return superheroDao.getAllSuperheroes();
    }

    @Override
    public List<Superhero> getSuperheroesSpottedAtSightingLocation(int locationId) {
        return superheroDao.getSuperheroesSpottedAtSightingLocation(locationId);
    }

    @Override
    public List<Superhero> getSuperheroesAffiliatedWithOrganization(int orgId) {
        return superheroDao.getSuperheroesAffiliatedWithOrganization(orgId);
    }

}
