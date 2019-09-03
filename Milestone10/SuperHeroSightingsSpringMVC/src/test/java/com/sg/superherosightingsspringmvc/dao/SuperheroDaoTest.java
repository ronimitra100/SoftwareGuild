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

public class SuperheroDaoTest {
    SuperheroDao superheroDao;
    LocationDao locationDao;
    OrganizationDao organizationDao;
    SightingDao sightingDao;
    SuperheroOrganizationAffiliationDao affiliationDao;
        
    public SuperheroDaoTest() {
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
     * TESTS RELATED TO VARIOUS CRUD OPERATIONS FOR SUPERHEROES 
     *=======================================================================================
     */
    @Test 
    public void testAddAndGetSuperhero() {
        Superhero hero = new Superhero();
        hero.setHeroName("Wonder Woman");
        hero.setHeroDescription("An Amazon Princess");
        hero.setHeroSuperPower("Can fly in the sky");
        superheroDao.addSuperhero(hero);
        
        Superhero fromDao = superheroDao.getSuperheroById(hero.getHeroId());
        
        assertEquals(hero, fromDao);
    }
    
    @Test
    public void testUpdateAndGetSuperhero() {
        
        Superhero hero = new Superhero();
        hero.setHeroName("Wonder Woman 2");
        hero.setHeroDescription("An Amazon Princess");
        hero.setHeroSuperPower("Can fly in the sky");
        superheroDao.addSuperhero(hero);
        
        Superhero fromDao = superheroDao.getSuperheroById(hero.getHeroId());
        assertEquals(hero, fromDao);
        
        hero.setHeroName("Black Panther");
        hero.setHeroDescription("Prince of Waganda");
        hero.setHeroSuperPower("Can turn into a luminous pather");
        superheroDao.updateSuperhero(hero);
        
        fromDao = superheroDao.getSuperheroById(hero.getHeroId());
        assertEquals(hero, fromDao);
    }
    
    @Test
    public void testDeleteSuperheroById() {
        Superhero hero = new Superhero();
        hero.setHeroName("Thor");
        hero.setHeroDescription("Prince of Assgard, God of War");
        hero.setHeroSuperPower("Can cause lighting with his hammer");
        superheroDao.addSuperhero(hero);
        
        Superhero fromDao = superheroDao.getSuperheroById(hero.getHeroId());
        assertEquals(hero, fromDao);
        
        superheroDao.deleteSuperheroById(hero.getHeroId());
        assertNull(superheroDao.getSuperheroById(hero.getHeroId()));
        
    }
    
    @Test
    public void testGetAllSuperheroes() {
        Superhero hero1 = new Superhero();
        hero1.setHeroName("Wonder Woman");
        hero1.setHeroDescription("An Amazon Princess");
        hero1.setHeroSuperPower("Can fly in the sky");
        superheroDao.addSuperhero(hero1);
        
        Superhero hero2 = new Superhero();
        hero2.setHeroName("Black Panther");
        hero2.setHeroDescription("Prince of Waganda");
        hero2.setHeroSuperPower("Can turn into a luminous pather");
        superheroDao.addSuperhero(hero2);
        
        Superhero hero3 = new Superhero();
        hero3.setHeroName("Thor");
        hero3.setHeroDescription("Prince of Assgard, God of War");
        hero3.setHeroSuperPower("Can cause lighting with his hammer");
        superheroDao.addSuperhero(hero3);
        
        assertEquals(3, superheroDao.getAllSuperheroes().size());        
    }
    
    /*=======================================================================================
     * TEST RELATED TO FETCHING SUPERHEROES AFFILIATED WITH A GIVEN ORGANIZATION
     *=======================================================================================
     */

   @Test
    public void testGetSuperheroesAffiliatedWithOrganization() {
        Organization org1 = new Organization();
        org1.setOrgName("Royal Academy");
        org1.setOrgDescription("Organization for every Royal Superhero");
        org1.setOrgAddress("Waganda");
        organizationDao.addOrganization(org1);
        assertEquals(org1, organizationDao.getOrgnizationById(org1.getOrgId()));
        
        Organization org2 = new Organization();
        org2.setOrgName("Marvel Org");
        org2.setOrgDescription("Organization for Marvel characters");
        org2.setOrgAddress("Marvel Universe");
        organizationDao.addOrganization(org2);
        assertEquals(org2, organizationDao.getOrgnizationById(org2.getOrgId()));
        
        Organization org3 = new Organization();
        org3.setOrgName("Star Wars Franchise");
        org3.setOrgDescription("Organization for Star War characters");
        org3.setOrgAddress("A galaxy far, far awy");
        organizationDao.addOrganization(org3);
        assertEquals(org3, organizationDao.getOrgnizationById(org3.getOrgId()));
        
        Superhero hero1 = new Superhero();
        hero1.setHeroName("Wonder Woman");
        hero1.setHeroDescription("An Amazon Princess");
        hero1.setHeroSuperPower("Can fly in the sky");
        superheroDao.addSuperhero(hero1);
        assertEquals(hero1, superheroDao.getSuperheroById(hero1.getHeroId()));
        
        Superhero hero2 = new Superhero();
        hero2.setHeroName("Black Panther");
        hero2.setHeroDescription("Prince of Waganda");
        hero2.setHeroSuperPower("Can turn into a luminous pather");
        superheroDao.addSuperhero(hero2);
        assertEquals(hero2, superheroDao.getSuperheroById(hero2.getHeroId()));
        
        Superhero hero3 = new Superhero();
        hero3.setHeroName("Thor");
        hero3.setHeroDescription("Prince of Assgard, God of War");
        hero3.setHeroSuperPower("Can cause lighting with his hammer");
        superheroDao.addSuperhero(hero3);
        
        SuperheroOrganizationAffiliation affiliation11 = new SuperheroOrganizationAffiliation();
        affiliation11.setHeroId(hero1.getHeroId());
        affiliation11.setOrgId(org1.getOrgId());
        affiliationDao.addSuperheroOrganizationAffiliation(affiliation11);
        
        SuperheroOrganizationAffiliation affiliation21 = new SuperheroOrganizationAffiliation();
        affiliation21.setHeroId(hero2.getHeroId());
        affiliation21.setOrgId(org1.getOrgId());
        affiliationDao.addSuperheroOrganizationAffiliation(affiliation21);
        
        SuperheroOrganizationAffiliation affiliation22 = new SuperheroOrganizationAffiliation();
        affiliation22.setHeroId(hero2.getHeroId());
        affiliation22.setOrgId(org2.getOrgId());
        affiliationDao.addSuperheroOrganizationAffiliation(affiliation22);
        
        assertEquals(2, superheroDao.getSuperheroesAffiliatedWithOrganization(org1.getOrgId()).size());
        assertEquals(1, superheroDao.getSuperheroesAffiliatedWithOrganization(org2.getOrgId()).size());
        assertEquals(0, superheroDao.getSuperheroesAffiliatedWithOrganization(org3.getOrgId()).size());
    }
    
    /*=======================================================================================
     * TEST RELATED TO FETCHING SUPERHEROES SIGHTED AT A GIVEN LOCATION
     *=======================================================================================
     */

    @Test
    public void testGetSuperheroesSpottedAtSightingLocation() {
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
        
        assertEquals(2, superheroDao.getSuperheroesSpottedAtSightingLocation(location3.getLocationId()).size());
        assertEquals(1, superheroDao.getSuperheroesSpottedAtSightingLocation(location.getLocationId()).size());      
        assertEquals(1, superheroDao.getSuperheroesSpottedAtSightingLocation(location2.getLocationId()).size());
    }
    

}