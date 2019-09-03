package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.*;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import java.time.*;

public interface SuperheroDao {
    
    public void addSuperhero(Superhero superhero);
    public Superhero getSuperheroById(int superheroId);
    public void updateSuperhero(Superhero superhero);
    public void deleteSuperheroById(int superheroId);
    public List<Superhero> getAllSuperheroes();
    public List<Superhero> getSuperheroesSpottedAtSightingLocation(int locationId);
    public List<Superhero> getSuperheroesAffiliatedWithOrganization(int orgId);
    
}
