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

public class SightingServiceLayerTest {

    SuperheroServiceLayer superheroService;
    LocationServiceLayer locationService;
    OrganizationServiceLayer organizationService;
    SightingServiceLayer sightingService;
    SuperheroOrganizationAffiliationServiceLayer affiliationService;
        
    public SightingServiceLayerTest() {
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
     * TESTS RELATED TO VARIOUS CRUD OPERATIONS FOR SIGHTINGS
     *=======================================================================================
     */
    
 
    @Test
    public void testAddGetSighting() {
        Superhero hero = new Superhero();
        hero.setHeroName("Wonder Woman");
        hero.setHeroDescription("An Amazon Princess");
        hero.setHeroSuperPower("Can fly in the sky");
        superheroService.addSuperhero(hero);
        
        Location location = new Location();
        location.setLocationName("Treasure Island");
        location.setLocationDescription("Hidden Secret");
        location.setLocationAddress("123 Vibranium Street, Waganda");
        location.setLatitude(-80.42);
        location.setLongitude(-19.86);
        locationService.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setHeroId(hero.getHeroId());
        sighting.setLocationId(location.getLocationId());
        sighting.setSightingTime(LocalDate.parse("2018-10-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        sightingService.addSighting(sighting);
        
        assertEquals(sighting, sightingService.getSightingById(sighting.getSightingId()));
    }

    
   @Test
    public void testUpdateGetSighting(){
        Superhero hero = new Superhero();
        hero.setHeroName("Wonder Woman");
        hero.setHeroDescription("An Amazon Princess");
        hero.setHeroSuperPower("Can fly in the sky");
        superheroService.addSuperhero(hero);
        
        Location location = new Location();
        location.setLocationName("Treasure Island");
        location.setLocationDescription("Hidden Secret");
        location.setLocationAddress("123 Vibranium Street, Waganda");
        location.setLatitude(-80.42);
        location.setLongitude(-19.86);
        locationService.addLocation(location);
        
        Sighting sighting = new Sighting();
        sighting.setHeroId(hero.getHeroId());
        sighting.setLocationId(location.getLocationId());
        sighting.setSightingTime(LocalDate.parse("2019-10-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        sightingService.addSighting(sighting);
        
        Superhero hero2 = new Superhero();
        hero2.setHeroName("Wonder Woman 2");
        hero2.setHeroDescription("A good and beatiful Amazon princess");
        hero2.setHeroSuperPower("Can generate lighting with her bracelets");
        superheroService.addSuperhero(hero2);
        
        Location location2 = new Location();
        location2.setLocationName("Crystal Palace ");
        location2.setLocationDescription("A big,beautiful palace");
        location2.setLocationAddress("123 Ocean Front");
        location2.setLatitude(25.42);
        location2.setLongitude(7.54);
        locationService.addLocation(location2);
        
        sighting.setHeroId(hero2.getHeroId());
        sighting.setLocationId(location2.getLocationId());
        sighting.setSightingTime(LocalDate.parse("2018-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        sightingService.updateSighting(sighting);
        
        Sighting fromService = sightingService.getSightingById(sighting.getSightingId());
        assertEquals(sighting, fromService);      
    }
     
    
    @Test
    public void testDeleteSighting() {
        Superhero hero = new Superhero();
        hero.setHeroName("The Hulk");
        hero.setHeroDescription("Huge Guy");
        hero.setHeroSuperPower("Great Wrestler");
        superheroService.addSuperhero(hero);
        
        Location location = new Location();
        location.setLocationName("Hulk House");
        location.setLocationDescription("Abode of Hulk in US");
        location.setLatitude(75.13);
        location.setLongitude(89.67);
        location.setLocationAddress("Hulk Street, Dayton, OH");
        locationService.addLocation(location);
        
        Sighting sighting = new Sighting();
        sighting.setHeroId(hero.getHeroId());
        sighting.setLocationId(location.getLocationId());
        sighting.setSightingTime(LocalDate.parse("2015-03-03", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        sightingService.addSighting(sighting);
        
        assertNotNull(sightingService.getSightingById(sighting.getSightingId()));
        sightingService.deleteSighting(sighting.getSightingId());
        assertNull(sightingService.getSightingById(sighting.getSightingId()));
    } 
    
    @Test
    public void testGetAllSightings() {
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
        sighting.setSightingTime(LocalDate.parse("2015-03-03", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
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
        sighting2.setHeroId(hero.getHeroId());
        sighting2.setLocationId(location.getLocationId());
        sighting2.setSightingTime(LocalDate.parse("2015-03-03", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        sightingService.addSighting(sighting2);
        
        assertEquals(2, sightingService.getAllSightings().size());     
    }
    
    /*=======================================================================================
     * TEST RELATED TO FETCHING SIGHTINGS ON A GIVEN DATE
     *=======================================================================================
     */
  
    @Test
    public void testGetSightingsByDate() {
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
        
        assertEquals(2, sightingService.getSightingsByDate(LocalDate.parse("2015-03-04", DateTimeFormatter.ofPattern("yyyy-MM-dd"))).size());
        assertEquals(1, sightingService.getSightingsByDate(LocalDate.parse("2019-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"))).size());
       
    }
    }
    
