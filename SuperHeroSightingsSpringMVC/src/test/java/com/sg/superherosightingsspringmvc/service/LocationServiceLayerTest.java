package com.sg.superherosightingsspringmvc.service;

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

public class LocationServiceLayerTest {

    SuperheroServiceLayer superheroService;
    LocationServiceLayer locationService;
    OrganizationServiceLayer organizationService;
    SightingServiceLayer sightingService;
    SuperheroOrganizationAffiliationServiceLayer affiliationService;
        
    public LocationServiceLayerTest() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        
        superheroService = ctx.getBean("superheroService", SuperheroServiceLayer.class);
        organizationService = ctx.getBean("organizationService", OrganizationServiceLayer.class);
        locationService = ctx.getBean("locationService", LocationServiceLayer.class);
        sightingService = ctx.getBean("sightingService", SightingServiceLayer.class);
        affiliationService = ctx.getBean("affiliationService", SuperheroOrganizationAffiliationServiceLayer.class);
        
        List<SuperheroOrganizationAffiliation> affiliations = affiliationService.getAllSuperheroOrganizationAffiliations();
        for (SuperheroOrganizationAffiliation affiliation : affiliations){
            affiliationService.deleteSuperheroOrganizationAffiliation(affiliation.getAffiliationId());
        }
        
        List<Sighting> sightings = sightingService.getAllSightings();
        for (Sighting currentSighting : sightings){
            sightingService.deleteSighting(currentSighting.getSightingId());
        }
        
        List<Superhero> superheroes = superheroService.getAllSuperheroes();
        for (Superhero currentHero : superheroes){
            superheroService.deleteSuperheroById(currentHero.getHeroId());
        }
        
        List<Organization> organizations = organizationService.getAllOrganizations();
        for (Organization currentOrg : organizations){
            organizationService.deleteOrganizationById(currentOrg.getOrgId());
        }
        
        List<Location> locations = locationService.getAllLocations();
        for (Location currentLocation : locations){
            locationService.deleteLocationById(currentLocation.getLocationId());
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
        locationService.addLocation(location);
        
        Location fromService = locationService.getLocationById(location.getLocationId());
        assertEquals(location, fromService);
    }

  
    @Test
    public void testUpdateAndGetLocation() {
        Location location = new Location();
        location.setLocationName("Waganda Townhall");
        location.setLocationDescription("A hidden paradise in Africa");
        location.setLocationAddress("Black Pather Street, Waganda");
        location.setLatitude(31.42);
        location.setLongitude(12.86);
        locationService.addLocation(location);
        
        Location fromService = locationService.getLocationById(location.getLocationId());
        assertEquals(location, fromService);
        
        location.setLocationName("Treasure Island");
        location.setLocationDescription("Hidden Secret");
        location.setLocationAddress("123 Vibranium Street, Waganda");
        location.setLatitude(-80.42);
        location.setLongitude(-19.86);
        locationService.updateLocation(location);
        
        fromService = locationService.getLocationById(location.getLocationId());
        assertEquals(location, fromService);
        
    }
    
     @Test
    public void testDeleteAndGetLocation() {
        Location location = new Location();
        location.setLocationName("Waganda Townhall");
        location.setLocationDescription("A hidden paradise in Africa");
        location.setLocationAddress("Black Pather Street, Waganda");
        location.setLatitude(31.42);
        location.setLongitude(12.86);
        locationService.addLocation(location);
        
        Location fromService = locationService.getLocationById(location.getLocationId());
        assertEquals(location, fromService);
        
        locationService.deleteLocationById(location.getLocationId());
        assertNull(locationService.getLocationById(location.getLocationId()));      
    }

    
    @Test
    public void testGetAllLocations() {
        Location location1 = new Location();
        location1.setLocationName("Waganda Townhall");
        location1.setLocationDescription("A hidden paradise in Africa");
        location1.setLocationAddress("Black Pather Street, Waganda");
        location1.setLatitude(31.42);
        location1.setLongitude(-12.86);
        locationService.addLocation(location1);
        
        Location location2 = new Location();
        location2.setLocationName("Castle of Wonders");
        location2.setLocationDescription("The Palace of Amazons");
        location2.setLocationAddress("123 Street of Wonders, Wonderland");
        location2.setLatitude(-10.26);
        location2.setLongitude(35.92);
        locationService.addLocation(location2);
        
        assertEquals(2,locationService.getAllLocations().size());      
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
        superheroService.addSuperhero(hero);
        
        Location location = new Location();
        location.setLocationName("Spiderverse");
        location.setLocationDescription("everywhere");
        location.setLatitude(12.13);
        location.setLongitude(45.67);
        location.setLocationAddress("123 Spider Street, New York ");
        locationService.addLocation(location);
        
        Sighting sighting = new Sighting();
        sighting.setHeroId(hero.getHeroId());
        sighting.setLocationId(location.getLocationId());
        sighting.setSightingTime(LocalDate.parse("2015-03-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        sightingService.addSighting(sighting);
        
        Superhero hero2 = new Superhero();
        hero2.setHeroName("Batman");
        hero2.setHeroDescription("Waers a black suit and a mask with batlike-ears");
        hero2.setHeroSuperPower("Can fly in the sky");
        superheroService.addSuperhero(hero2);
        
        Location location2 = new Location();
        location2.setLocationName("Batman World");
        location2.setLocationDescription("Batman's favorite park");
        location2.setLatitude(9.76);
        location2.setLongitude(5.67);
        location2.setLocationAddress("123 Spider Street, Los Angeles");
        locationService.addLocation(location2);
        
        Sighting sighting2 = new Sighting();
        sighting2.setHeroId(hero2.getHeroId());
        sighting2.setLocationId(location2.getLocationId());
        sighting2.setSightingTime(LocalDate.parse("2015-03-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        sightingService.addSighting(sighting2);
        
        Superhero hero3 = new Superhero();
        hero3.setHeroName("Wonder Woman 4");
        hero3.setHeroDescription("Amazon Princess");
        hero3.setHeroSuperPower("Can fly in the sky");
        superheroService.addSuperhero(hero3);
        
        Location location3 = new Location();
        location3.setLocationName("Wonderland");
        location3.setLocationDescription("Paradise on Earth");
        location3.setLatitude(29.76);
        location3.setLongitude(35.67);
        location3.setLocationAddress("123 Wonderway, Wonderland");
        locationService.addLocation(location3);
        
        Sighting sighting3 = new Sighting();
        sighting3.setHeroId(hero3.getHeroId());
        sighting3.setLocationId(location3.getLocationId());
        sighting3.setSightingTime(LocalDate.parse("2019-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        sightingService.addSighting(sighting3);
        
        Sighting sighting4 = new Sighting();
        sighting4.setHeroId(hero2.getHeroId());
        sighting4.setLocationId(location3.getLocationId());
        sighting4.setSightingTime(LocalDate.parse("2019-02-02", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        sightingService.addSighting(sighting4);
        
        assertEquals(2, locationService.getLocationsVisitedBySuperhero(hero2.getHeroId()).size());
        assertEquals(1, locationService.getLocationsVisitedBySuperhero(hero.getHeroId()).size());
        assertEquals(1, locationService.getLocationsVisitedBySuperhero(hero3.getHeroId()).size());
        
    }
}