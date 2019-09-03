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

public class OrganizationServiceLayerTest {

    SuperheroServiceLayer superheroService;
    LocationServiceLayer locationService;
    OrganizationServiceLayer organizationService;
    SightingServiceLayer sightingService;
    SuperheroOrganizationAffiliationServiceLayer affiliationService;
        
    public OrganizationServiceLayerTest() {
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
     * TESTS RELATED TO VARIOUS CRUD OPERATIONS FOR ORGANIZATIONS 
     *=======================================================================================
     */
    
    @Test
    public void testAddAndGetOrganization() {
        Organization org = new Organization();
        org.setOrgName("Princely Organization");
        org.setOrgDescription("Organization for every Superhero Prince");
        org.setOrgAddress("Waganda");
        organizationService.addOrganization(org);
        
        Organization fromService = organizationService.getOrgnizationById(org.getOrgId());
        
        assertEquals(org, fromService);
    }
    
    @Test
    public void testUpdateAndGetOrganization() {
        Organization org = new Organization();
        org.setOrgName("Marvel Org");
        org.setOrgDescription("Organization for Marvel Superheroes");
        org.setOrgAddress("Waganda");
        organizationService.addOrganization(org);
        
        Organization fromService = organizationService.getOrgnizationById(org.getOrgId());
        assertEquals(org, fromService);
        
        org.setOrgName("DC Org");
        org.setOrgDescription("Organization for DC Comic characters");
        org.setOrgAddress("Themyscira");
        organizationService.updateOrganization(org);
        
        fromService = organizationService.getOrgnizationById(org.getOrgId());
        assertEquals(org, fromService);
    }
    
    @Test
    public void testDeleteAndGetOrganizationById() {
        Organization org = new Organization();
        org.setOrgName("Princely Organization");
        org.setOrgDescription("Organization for every Superhero Prince");
        org.setOrgAddress("Waganda");
        organizationService.addOrganization(org);
        
        Organization fromService = organizationService.getOrgnizationById(org.getOrgId());        
        assertEquals(org, fromService);
        
        organizationService.deleteOrganizationById(org.getOrgId());
        assertNull(organizationService.getOrgnizationById(org.getOrgId()));       
    }
    
     @Test
    public void testGetAllOrganizations() {
        Organization org1 = new Organization();
        org1.setOrgName("Royal Academy");
        org1.setOrgDescription("Organization for every Royal Superhero");
        org1.setOrgAddress("Waganda");
        organizationService.addOrganization(org1);
        
        Organization org2 = new Organization();
        org2.setOrgName("Lands of Amazons");
        org2.setOrgDescription("Organization for Superheroines");
        org2.setOrgAddress("Themyscira");
        organizationService.addOrganization(org2);
        
        Organization org3 = new Organization();
        org3.setOrgName("Marvel Org");
        org3.setOrgDescription("Organization for Marvel characters");
        org3.setOrgAddress("Marvel Universe");
        organizationService.addOrganization(org3);
        
        assertEquals(3, organizationService.getAllOrganizations().size());
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
        organizationService.addOrganization(org1);
        assertEquals(org1, organizationService.getOrgnizationById(org1.getOrgId()));
        
        Organization org2 = new Organization();
        org2.setOrgName("Marvel Org");
        org2.setOrgDescription("Organization for Marvel characters");
        org2.setOrgAddress("Marvel Universe");
        organizationService.addOrganization(org2);
        assertEquals(org2, organizationService.getOrgnizationById(org2.getOrgId()));
        
        Organization org3 = new Organization();
        org3.setOrgName("Star Wars Franchise");
        org3.setOrgDescription("Organization for Star War characters");
        org3.setOrgAddress("A galaxy far, far awy");
        organizationService.addOrganization(org3);
        assertEquals(org3, organizationService.getOrgnizationById(org3.getOrgId()));
        
        Superhero hero1 = new Superhero();
        hero1.setHeroName("Wonder Woman");
        hero1.setHeroDescription("An Amazon Princess");
        hero1.setHeroSuperPower("Can fly in the sky");
        superheroService.addSuperhero(hero1);
        assertEquals(hero1, superheroService.getSuperheroById(hero1.getHeroId()));
        
        Superhero hero2 = new Superhero();
        hero2.setHeroName("Black Panther");
        hero2.setHeroDescription("Prince of Waganda");
        hero2.setHeroSuperPower("Can turn into a luminous pather");
        superheroService.addSuperhero(hero2);
        assertEquals(hero2, superheroService.getSuperheroById(hero2.getHeroId()));
        
        Superhero hero3 = new Superhero();
        hero3.setHeroName("Thor");
        hero3.setHeroDescription("Prince of Assgard, God of War");
        hero3.setHeroSuperPower("Can cause lighting with his hammer");
        superheroService.addSuperhero(hero3);
        
        SuperheroOrganizationAffiliation affiliation11 = new SuperheroOrganizationAffiliation();
        affiliation11.setHeroId(hero1.getHeroId());
        affiliation11.setOrgId(org1.getOrgId());
        affiliationService.addSuperheroOrganizationAffiliation(affiliation11);
        
        SuperheroOrganizationAffiliation affiliation21 = new SuperheroOrganizationAffiliation();
        affiliation21.setHeroId(hero2.getHeroId());
        affiliation21.setOrgId(org1.getOrgId());
        affiliationService.addSuperheroOrganizationAffiliation(affiliation21);
        
        SuperheroOrganizationAffiliation affiliation22 = new SuperheroOrganizationAffiliation();
        affiliation22.setHeroId(hero2.getHeroId());
        affiliation22.setOrgId(org2.getOrgId());
        affiliationService.addSuperheroOrganizationAffiliation(affiliation22);
        
        assertEquals(2, organizationService.getOrganizationsAffliatedWithSuperhero(hero2.getHeroId()).size());
        assertEquals(1, organizationService.getOrganizationsAffliatedWithSuperhero(hero1.getHeroId()).size());
        assertEquals(0, organizationService.getOrganizationsAffliatedWithSuperhero(hero3.getHeroId()).size());
    }
    

}