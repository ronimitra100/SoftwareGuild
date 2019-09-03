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

public class SightingDaoTest {
    SuperheroDao superheroDao;
    LocationDao locationDao;
    OrganizationDao organizationDao;
    SightingDao sightingDao;
    SuperheroOrganizationAffiliationDao affiliationDao;

    public SightingDaoTest() {
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
     * TESTS RELATED TO VARIOUS CRUD OPERATIONS FOR SIGHTINGS
     *=======================================================================================
     */
 
    @Test
    public void testAddGetSighting() {
        Superhero hero = new Superhero();
        hero.setHeroName("Wonder Woman");
        hero.setHeroDescription("An Amazon Princess");
        hero.setHeroSuperPower("Can fly in the sky");
        superheroDao.addSuperhero(hero);
        
        Location location = new Location();
        location.setLocationName("Treasure Island");
        location.setLocationDescription("Hidden Secret");
        location.setLocationAddress("123 Vibranium Street, Waganda");
        location.setLatitude(-80.42);
        location.setLongitude(-19.86);
        locationDao.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setHeroId(hero.getHeroId());
        sighting.setLocationId(location.getLocationId());
        sighting.setSightingTime(LocalDate.parse("2018-10-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        sightingDao.addSighting(sighting);
        
        assertEquals(sighting, sightingDao.getSightingById(sighting.getSightingId()));
    }

    
   @Test
    public void testUpdateGetSighting() {
        Superhero hero = new Superhero();
        hero.setHeroName("Wonder Woman");
        hero.setHeroDescription("An Amazon Princess");
        hero.setHeroSuperPower("Can fly in the sky");
        superheroDao.addSuperhero(hero);
        
        Location location = new Location();
        location.setLocationName("Treasure Island");
        location.setLocationDescription("Hidden Secret");
        location.setLocationAddress("123 Vibranium Street, Waganda");
        location.setLatitude(-80.42);
        location.setLongitude(-19.86);
        locationDao.addLocation(location);
        
        Sighting sighting = new Sighting();
        sighting.setHeroId(hero.getHeroId());
        sighting.setLocationId(location.getLocationId());
        sighting.setSightingTime(LocalDate.parse("2019-10-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        sightingDao.addSighting(sighting);
        
        Superhero hero2 = new Superhero();
        hero2.setHeroName("Wonder Woman 2");
        hero2.setHeroDescription("A good and beatiful Amazon princess");
        hero2.setHeroSuperPower("Can generate lighting with her bracelets");
        superheroDao.addSuperhero(hero2);
        
        Location location2 = new Location();
        location2.setLocationName("Crystal Palace ");
        location2.setLocationDescription("A big,beautiful palace");
        location2.setLocationAddress("123 Ocean Front");
        location2.setLatitude(25.42);
        location2.setLongitude(7.54);
        locationDao.addLocation(location2);
        
        sighting.setHeroId(hero2.getHeroId());
        sighting.setLocationId(location2.getLocationId());
        sighting.setSightingTime(LocalDate.parse("2018-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        sightingDao.updateSighting(sighting);
        
        Sighting fromDao = sightingDao.getSightingById(sighting.getSightingId());
        assertEquals(sighting, fromDao);      
    }
     
    
    @Test
    public void testDeleteSighting() {
        Superhero hero = new Superhero();
        hero.setHeroName("The Hulk");
        hero.setHeroDescription("Huge Guy");
        hero.setHeroSuperPower("Great Wrestler");
        superheroDao.addSuperhero(hero);
        
        Location location = new Location();
        location.setLocationName("Hulk House");
        location.setLocationDescription("Abode of Hulk in US");
        location.setLatitude(75.13);
        location.setLongitude(89.67);
        location.setLocationAddress("Hulk Street, Dayton, OH");
        locationDao.addLocation(location);
        
        Sighting sighting = new Sighting();
        sighting.setHeroId(hero.getHeroId());
        sighting.setLocationId(location.getLocationId());
        sighting.setSightingTime(LocalDate.parse("2015-03-03", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        sightingDao.addSighting(sighting);
        
        assertNotNull(sightingDao.getSightingById(sighting.getSightingId()));
        sightingDao.deleteSighting(sighting.getSightingId());
        assertNull(sightingDao.getSightingById(sighting.getSightingId()));
    } 
    
    @Test
    public void testGetAllSightings() {
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
        sighting.setSightingTime(LocalDate.parse("2015-03-03", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
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
        sighting2.setHeroId(hero.getHeroId());
        sighting2.setLocationId(location.getLocationId());
        sighting2.setSightingTime(LocalDate.parse("2015-03-03", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        sightingDao.addSighting(sighting2);
        
        assertEquals(2, sightingDao.getAllSightings().size());     
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
        
        assertEquals(2, sightingDao.getSightingsByDate(LocalDate.parse("2015-03-04", DateTimeFormatter.ofPattern("yyyy-MM-dd"))).size());
        assertEquals(1, sightingDao.getSightingsByDate(LocalDate.parse("2019-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"))).size());
       
    }
    

}