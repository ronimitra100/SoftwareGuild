package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LocationDaoTest {
    SuperheroDao superheroDao;
    LocationDao locationDao;
    OrganizationDao organizationDao;
    SightingDao sightingDao;
    SuperheroOrganizationAffiliationDao affiliationDao;

    public LocationDaoTest() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        
        superheroDao = ctx.getBean("superheroDao", SuperheroDao.class);
        organizationDao = ctx.getBean("organizationDao", OrganizationDao.class);
        locationDao = ctx.getBean("locationDao", LocationDao.class);
        sightingDao = ctx.getBean("sightingDao", SightingDao.class);
        affiliationDao = ctx.getBean("affiliationDao", SuperheroOrganizationAffiliationDao.class);
        
        List<SuperheroOrganizationAffiliation> affiliations = affiliationDao.getAllSuperheroOrganizationAffiliations();
        for (SuperheroOrganizationAffiliation affiliation : affiliations){
            affiliationDao.deleteSuperheroOrganizationAffiliation(affiliation.getAffiliationId());
        }
        
        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting currentSighting : sightings){
            sightingDao.deleteSighting(currentSighting.getSightingId());
        }
        
        List<Superhero> superheroes = superheroDao.getAllSuperheroes();
        for (Superhero currentHero : superheroes){
            superheroDao.deleteSuperheroById(currentHero.getHeroId());
        }
        
        List<Organization> organizations = organizationDao.getAllOrganizations();
        for (Organization currentOrg : organizations){
            organizationDao.deleteOrganizationById(currentOrg.getOrgId());
        }
        
        List<Location> locations = locationDao.getAllLocations();
        for (Location currentLocation : locations){
            locationDao.deleteLocationById(currentLocation.getLocationId());
        }            
    }
    
    /*=======================================================================================
     * TESTS RELATED TO VARIOUS CRUD OPERATIONS FOR LOCATIONS
     *=======================================================================================
     */
    
     @Test
    public void testAddAndGetLocation() {      
        Location location = new Location();
        location.setLocationName("Waganda Townhall");
        location.setLocationDescription("A hidden paradise in Africa");
        location.setLocationAddress("Black Pather Street, Waganda");
        location.setLatitude(31.42);
        location.setLongitude(12.86);
        locationDao.addLocation(location);
        
        Location fromDao = locationDao.getLocationById(location.getLocationId());
        assertEquals(location, fromDao);
    }

  
    @Test
    public void testUpdateAndGetLocation() {
        Location location = new Location();
        location.setLocationName("Waganda Townhall");
        location.setLocationDescription("A hidden paradise in Africa");
        location.setLocationAddress("Black Pather Street, Waganda");
        location.setLatitude(31.42);
        location.setLongitude(12.86);
        locationDao.addLocation(location);
        
        Location fromDao = locationDao.getLocationById(location.getLocationId());
        assertEquals(location, fromDao);
        
        location.setLocationName("Treasure Island");
        location.setLocationDescription("Hidden Secret");
        location.setLocationAddress("123 Vibranium Street, Waganda");
        location.setLatitude(-80.42);
        location.setLongitude(-19.86);
        locationDao.updateLocation(location);
        
        fromDao = locationDao.getLocationById(location.getLocationId());
        assertEquals(location, fromDao);
        
    }
    
     @Test
    public void testDeleteAndGetLocation() {
        Location location = new Location();
        location.setLocationName("Waganda Townhall");
        location.setLocationDescription("A hidden paradise in Africa");
        location.setLocationAddress("Black Pather Street, Waganda");
        location.setLatitude(31.42);
        location.setLongitude(12.86);
        locationDao.addLocation(location);
        
        Location fromDao = locationDao.getLocationById(location.getLocationId());
        assertEquals(location, fromDao);
        
        locationDao.deleteLocationById(location.getLocationId());
        assertNull(locationDao.getLocationById(location.getLocationId()));      
    }

    
    @Test
    public void testGetAllLocations() {
        Location location1 = new Location();
        location1.setLocationName("Waganda Townhall");
        location1.setLocationDescription("A hidden paradise in Africa");
        location1.setLocationAddress("Black Pather Street, Waganda");
        location1.setLatitude(31.42);
        location1.setLongitude(-12.86);
        locationDao.addLocation(location1);
        
        Location location2 = new Location();
        location2.setLocationName("Castle of Wonders");
        location2.setLocationDescription("The Palace of Amazons");
        location2.setLocationAddress("123 Street of Wonders, Wonderland");
        location2.setLatitude(-10.26);
        location2.setLongitude(35.92);
        locationDao.addLocation(location2);
        
        assertEquals(2,locationDao.getAllLocations().size());      
    }
    
    
    /*=======================================================================================
     * TEST RELATED TO FETCHING LOCATIONS VISITED BY SUPERHERO
     *=======================================================================================
     */    
    @Test
    public void testGetLocationsVisitedBySuperhero() {
        Superhero hero = new Superhero();
        hero.setHeroName("Spiderman");
        hero.setHeroDescription("Wears blue and red suit");
        hero.setHeroSuperPower("Can jump from building to building using spider nets");
        superheroDao.addSuperhero(hero);
        
        Location location = new Location();
        location.setLocationName("Spiderverse");
        location.setLocationDescription("everywhere");
        location.setLatitude(12.13);
        location.setLongitude(45.67);
        location.setLocationAddress("123 Spider Street, New York ");
        locationDao.addLocation(location);
        
        Sighting sighting = new Sighting();
        sighting.setHeroId(hero.getHeroId());
        sighting.setLocationId(location.getLocationId());
        sighting.setSightingTime(LocalDate.parse("2015-03-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        sightingDao.addSighting(sighting);
        
        Superhero hero2 = new Superhero();
        hero2.setHeroName("Batman");
        hero2.setHeroDescription("Waers a black suit and a mask with batlike-ears");
        hero2.setHeroSuperPower("Can fly in the sky");
        superheroDao.addSuperhero(hero2);
        
        Location location2 = new Location();
        location2.setLocationName("Batman World");
        location2.setLocationDescription("Batman's favorite park");
        location2.setLatitude(9.76);
        location2.setLongitude(5.67);
        location2.setLocationAddress("123 Spider Street, Los Angeles");
        locationDao.addLocation(location2);
        
        Sighting sighting2 = new Sighting();
        sighting2.setHeroId(hero2.getHeroId());
        sighting2.setLocationId(location2.getLocationId());
        sighting2.setSightingTime(LocalDate.parse("2015-03-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        sightingDao.addSighting(sighting2);
        
        Superhero hero3 = new Superhero();
        hero3.setHeroName("Wonder Woman 4");
        hero3.setHeroDescription("Amazon Princess");
        hero3.setHeroSuperPower("Can fly in the sky");
        superheroDao.addSuperhero(hero3);
        
        Location location3 = new Location();
        location3.setLocationName("Wonderland");
        location3.setLocationDescription("Paradise on Earth");
        location3.setLatitude(29.76);
        location3.setLongitude(35.67);
        location3.setLocationAddress("123 Wonderway, Wonderland");
        locationDao.addLocation(location3);
        
        Sighting sighting3 = new Sighting();
        sighting3.setHeroId(hero3.getHeroId());
        sighting3.setLocationId(location3.getLocationId());
        sighting3.setSightingTime(LocalDate.parse("2019-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        sightingDao.addSighting(sighting3);
        
        Sighting sighting4 = new Sighting();
        sighting4.setHeroId(hero2.getHeroId());
        sighting4.setLocationId(location3.getLocationId());
        sighting4.setSightingTime(LocalDate.parse("2019-02-02", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        sightingDao.addSighting(sighting4);
        
        assertEquals(2, locationDao.getLocationsVisitedBySuperhero(hero2.getHeroId()).size());
        assertEquals(1, locationDao.getLocationsVisitedBySuperhero(hero.getHeroId()).size());
        assertEquals(1, locationDao.getLocationsVisitedBySuperhero(hero3.getHeroId()).size());
        
    }
    

}