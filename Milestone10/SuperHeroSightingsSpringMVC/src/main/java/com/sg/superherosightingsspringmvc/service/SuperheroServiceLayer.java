package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.model.*;
import com.sg.superherosightingsspringmvc.dao.*;
import java.util.List;

public interface SuperheroServiceLayer {
    public void addSuperhero(Superhero superhero);
    public Superhero getSuperheroById(int superheroId);
    public void updateSuperhero(Superhero superhero);
    public void deleteSuperheroById(int superheroId);
    public List<Superhero> getAllSuperheroes();
    public List<Superhero> getSuperheroesSpottedAtSightingLocation(int locationId);
    public List<Superhero> getSuperheroesAffiliatedWithOrganization(int orgId);
}
