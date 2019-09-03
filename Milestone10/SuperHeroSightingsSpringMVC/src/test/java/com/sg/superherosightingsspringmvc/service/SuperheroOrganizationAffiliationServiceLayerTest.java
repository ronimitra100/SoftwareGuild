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

public class SuperheroOrganizationAffiliationServiceLayerTest {

    SuperheroServiceLayer superheroService;
    LocationServiceLayer locationService;
    OrganizationServiceLayer organizationService;
    SightingServiceLayer sightingService;
    SuperheroOrganizationAffiliationServiceLayer affiliationService;
        
    public SuperheroOrganizationAffiliationServiceLayerTest() {
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
    
    @Test
    public void testAddGetSuperheroOrganizationAffiliations(){
        Organization org1 = new Organization();
        org1.setOrgName("Royal Academy");
        org1.setOrgDescription("Organization for every Royal Superhero");
        org1.setOrgAddress("Waganda");
        organizationService.addOrganization(org1);
        assertEquals(org1, organizationService.getOrgnizationById(org1.getOrgId()));
        
        Superhero hero1 = new Superhero();
        hero1.setHeroName("Wonder Woman");
        hero1.setHeroDescription("An Amazon Princess");
        hero1.setHeroSuperPower("Can fly in the sky");
        superheroService.addSuperhero(hero1);
        assertEquals(hero1, superheroService.getSuperheroById(hero1.getHeroId()));
        
        SuperheroOrganizationAffiliation affiliation11 = new SuperheroOrganizationAffiliation();
        affiliation11.setHeroId(hero1.getHeroId());
        affiliation11.setOrgId(org1.getOrgId());
        affiliationService.addSuperheroOrganizationAffiliation(affiliation11);
        
        SuperheroOrganizationAffiliation fromService = affiliationService.getSuperheroOrganizationAffiliation(affiliation11.getAffiliationId());
        assertEquals(affiliation11, fromService);
    }
    
    @Test
    public void testUpdateGetSuperheroOrganizationAffiliations(){
        Organization org1 = new Organization();
        org1.setOrgName("Royal Academy");
        org1.setOrgDescription("Organization for every Royal Superhero");
        org1.setOrgAddress("Waganda");
        organizationService.addOrganization(org1);
        assertEquals(org1, organizationService.getOrgnizationById(org1.getOrgId()));
        
        Superhero hero1 = new Superhero();
        hero1.setHeroName("Wonder Woman");
        hero1.setHeroDescription("An Amazon Princess");
        hero1.setHeroSuperPower("Can fly in the sky");
        superheroService.addSuperhero(hero1);
        assertEquals(hero1, superheroService.getSuperheroById(hero1.getHeroId()));
        
        SuperheroOrganizationAffiliation affiliation11 = new SuperheroOrganizationAffiliation();
        affiliation11.setHeroId(hero1.getHeroId());
        affiliation11.setOrgId(org1.getOrgId());
        affiliationService.addSuperheroOrganizationAffiliation(affiliation11);
        
        Superhero hero2 = new Superhero();
        hero2.setHeroName("Black Panther");
        hero2.setHeroDescription("Prince of Waganda");
        hero2.setHeroSuperPower("Can turn into a luminous pather");
        superheroService.addSuperhero(hero2);
        assertEquals(hero2, superheroService.getSuperheroById(hero2.getHeroId()));
        
        Organization org2 = new Organization();
        org2.setOrgName("Marvel Org");
        org2.setOrgDescription("Organization for Marvel characters");
        org2.setOrgAddress("Marvel Universe");
        organizationService.addOrganization(org2);
        assertEquals(org2, organizationService.getOrgnizationById(org2.getOrgId()));
        
        affiliation11.setHeroId(hero2.getHeroId());
        affiliation11.setOrgId(org2.getOrgId());
        affiliationService.updateSuperheroOrganizationAffiliation(affiliation11);
        assertEquals(hero2.getHeroId(), affiliation11.getHeroId());
        assertEquals(org2.getOrgId(), affiliation11.getOrgId());
        
        SuperheroOrganizationAffiliation fromService = affiliationService.getSuperheroOrganizationAffiliation(affiliation11.getAffiliationId());
        assertEquals(affiliation11, fromService);
    }
    
    @Test
    public void testDeleteGetSuperheroOrganizationAffiliations(){
        Organization org1 = new Organization();
        org1.setOrgName("Royal Academy");
        org1.setOrgDescription("Organization for every Royal Superhero");
        org1.setOrgAddress("Waganda");
        organizationService.addOrganization(org1);
        assertEquals(org1, organizationService.getOrgnizationById(org1.getOrgId()));
        
        Superhero hero1 = new Superhero();
        hero1.setHeroName("Wonder Woman");
        hero1.setHeroDescription("An Amazon Princess");
        hero1.setHeroSuperPower("Can fly in the sky");
        superheroService.addSuperhero(hero1);
        assertEquals(hero1, superheroService.getSuperheroById(hero1.getHeroId()));
        
        SuperheroOrganizationAffiliation affiliation11 = new SuperheroOrganizationAffiliation();
        affiliation11.setHeroId(hero1.getHeroId());
        affiliation11.setOrgId(org1.getOrgId());
        affiliationService.addSuperheroOrganizationAffiliation(affiliation11);
        
        SuperheroOrganizationAffiliation fromService = affiliationService.getSuperheroOrganizationAffiliation(affiliation11.getAffiliationId());
        assertEquals(affiliation11, fromService);
        assertNotNull(fromService);
        int affiliationId = affiliation11.getAffiliationId();
        
        affiliationService.deleteSuperheroOrganizationAffiliation(affiliationId);
        assertNull(affiliationService.getSuperheroOrganizationAffiliation(affiliationId));
 
    }
    
    @Test
    public void getAllSuperheroOrganizationAffiliations(){
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
        
        assertEquals(3, affiliationService.getAllSuperheroOrganizationAffiliations().size());
    }   

    
}