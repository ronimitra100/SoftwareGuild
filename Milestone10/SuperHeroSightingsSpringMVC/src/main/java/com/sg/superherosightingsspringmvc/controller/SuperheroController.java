package com.sg.superherosightingsspringmvc.controller;

import com.sg.superherosightingsspringmvc.service.*;
import com.sg.superherosightingsspringmvc.model.*;
import com.sg.superherosightingsspringmvc.dao.*;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SuperheroController {
    SuperheroServiceLayer superheroService;
    OrganizationServiceLayer organizationService;
    LocationServiceLayer locationService;
    SightingServiceLayer sightingService;
    SuperheroOrganizationAffiliationServiceLayer affiliationService;
    
    @Inject
    public SuperheroController(SuperheroServiceLayer superheroService, OrganizationServiceLayer organizationService, LocationServiceLayer locationService, SightingServiceLayer sightingService, SuperheroOrganizationAffiliationServiceLayer affiliationService){
        this.superheroService = superheroService;
        this.organizationService = organizationService;
        this.locationService = locationService;
        this.sightingService = sightingService;
        this.affiliationService = affiliationService;
    }
    
    @RequestMapping(value="/displaySuperheroDetails", method=RequestMethod.GET)
    public String displaySuperheroes(HttpServletRequest request, Model model){
        String heroIdParameter = request.getParameter("heroId");
        int heroId = Integer.parseInt(heroIdParameter);
        Superhero hero = superheroService.getSuperheroById(heroId);
        model.addAttribute("superhero", hero);
        
        List<Organization> organizationList = organizationService.getOrganizationsAffliatedWithSuperhero(heroId);
        model.addAttribute("organizationList", organizationList);
        
        List<Location> locationList = locationService.getLocationsVisitedBySuperhero(heroId);
        model.addAttribute("locationList", locationList);
        return "superheroDetails";
    }
    
    @RequestMapping(value="/createSuperhero", method=RequestMethod.POST)
    public String createSuperhero(@Valid @ModelAttribute("superhero") Superhero hero, BindingResult result,HttpServletRequest request){
        if (result.hasErrors()){
          return "redirect:displaySuperheroesPage";
      }
        Superhero superhero = new Superhero();
        superhero.setHeroName(request.getParameter("heroName"));
        superhero.setHeroDescription(request.getParameter("heroDescription"));
        superhero.setHeroSuperPower(request.getParameter("heroSuperPower"));
        superheroService.addSuperhero(superhero);
        
        return "redirect:displaySuperheroesPage";
    }
    
    @RequestMapping(value="/displaySuperheroesPage", method=RequestMethod.GET)
    public String displaySuperheroessPage(Model model){
        List<Superhero> superheroList = superheroService.getAllSuperheroes();
        model.addAttribute("superheroList", superheroList);
         
        return "superheroes";
    }
    
    @RequestMapping(value="/deleteSuperhero", method=RequestMethod.GET)
    public String deleteSuperhero(HttpServletRequest request){
        String heroIdParameter = request.getParameter("heroId");
        int heroId = Integer.parseInt(heroIdParameter);
        //Superhero hero = superheroService.getSuperheroById(heroId);
        superheroService.deleteSuperheroById(heroId);
        return "redirect:displaySuperheroesPage";
    }
    
    @RequestMapping(value="/displayEditSuperheroForm",method=RequestMethod.GET)
    public String displayEditSuperheroForm(HttpServletRequest request,Model model){
        String heroIdParameter = request.getParameter("heroId");
        int heroId = Integer.parseInt(heroIdParameter);
        
        Superhero hero = superheroService.getSuperheroById(heroId);
        model.addAttribute("superhero", hero);
       
        
        return "editSuperheroForm";
    }
    
    @RequestMapping(value="/editSuperhero", method=RequestMethod.POST)
    public String editSuperhero(@Valid @ModelAttribute("superhero") Superhero hero, BindingResult result){
      if (result.hasErrors()){
          return "editSuperheroForm";
      }
       superheroService.updateSuperhero(hero);
       
       return "redirect:displaySuperheroesPage";
    }

}
