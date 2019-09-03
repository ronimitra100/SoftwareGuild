package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.*;
import com.sg.superherosightingsspringmvc.model.Organization;
import com.sg.superherosightingsspringmvc.model.Sighting;
import com.sg.superherosightingsspringmvc.model.Superhero;
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

public class OrganizationDaoTest {
    SuperheroDao superheroDao;
    LocationDao locationDao;
    OrganizationDao organizationDao;
    SightingDao sightingDao;
    SuperheroOrganizationAffiliationDao affiliationDao;

    public OrganizationDaoTest() {
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
     * TESTS RELATED TO VARIOUS CRUD OPERATIONS FOR ORGANIZATIONS 
     *=======================================================================================
     */
    
    @Test
    public void testAddAndGetOrganization() {
        Organization org = new Organization();
        org.setOrgName("Princely Organization");
        org.setOrgDescription("Organization for every Superhero Prince");
        org.setOrgAddress("Waganda");
        organizationDao.addOrganization(org);
        
        Organization fromDao = organizationDao.getOrgnizationById(org.getOrgId());
        
        assertEquals(org, fromDao);
    }
    
    @Test
    public void testUpdateAndGetOrganization() {
        Organization org = new Organization();
        org.setOrgName("Marvel Org");
        org.setOrgDescription("Organization for Marvel Superheroes");
        org.setOrgAddress("Waganda");
        organizationDao.addOrganization(org);
        
        Organization fromDao = organizationDao.getOrgnizationById(org.getOrgId());
        assertEquals(org, fromDao);
        
        org.setOrgName("DC Org");
        org.setOrgDescription("Organization for DC Comic characters");
        org.setOrgAddress("Themyscira");
        organizationDao.updateOrganization(org);
        
        fromDao = organizationDao.getOrgnizationById(org.getOrgId());
        assertEquals(org, fromDao);
    }
    
    @Test
    public void testDeleteAndGetOrganizationById() {
        Organization org = new Organization();
        org.setOrgName("Princely Organization");
        org.setOrgDescription("Organization for every Superhero Prince");
        org.setOrgAddress("Waganda");
        organizationDao.addOrganization(org);
        
        Organization fromDao = organizationDao.getOrgnizationById(org.getOrgId());        
        assertEquals(org, fromDao);
        
        organizationDao.deleteOrganizationById(org.getOrgId());
        assertNull(organizationDao.getOrgnizationById(org.getOrgId()));       
    }
    
     @Test
    public void testGetAllOrganizations() {
        Organization org1 = new Organization();
        org1.setOrgName("Royal Academy");
        org1.setOrgDescription("Organization for every Royal Superhero");
        org1.setOrgAddress("Waganda");
        organizationDao.addOrganization(org1);
        
        Organization org2 = new Organization();
        org2.setOrgName("Lands of Amazons");
        org2.setOrgDescription("Organization for Superheroines");
        org2.setOrgAddress("Themyscira");
        organizationDao.addOrganization(org2);
        
        Organization org3 = new Organization();
        org3.setOrgName("Marvel Org");
        org3.setOrgDescription("Organization for Marvel characters");
        org3.setOrgAddress("Marvel Universe");
        organizationDao.addOrganization(org3);
        
        assertEquals(3, organizationDao.getAllOrganizations().size());
    }
    
    /*=======================================================================================
     * TEST RELATED TO FETCHING ORGANIZATIONS AFFILIATED WITH A GIVEN SUPERHERO
     *=======================================================================================
     */
    @Test
    public void testGetOrganizationsAffliatedWithSuperhero() {
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
        
        assertEquals(2, organizationDao.getOrganizationsAffliatedWithSuperhero(hero2.getHeroId()).size());
        assertEquals(1, organizationDao.getOrganizationsAffliatedWithSuperhero(hero1.getHeroId()).size());
        assertEquals(0, organizationDao.getOrganizationsAffliatedWithSuperhero(hero3.getHeroId()).size());
    }


}