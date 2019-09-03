package com.sg.superherosightingsspringmvc.controller;

import com.sg.superherosightingsspringmvc.service.*;
import com.sg.superherosightingsspringmvc.model.*;
import com.sg.superherosightingsspringmvc.dao.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
public class SightingController {
    SightingServiceLayer sightingService;
    SuperheroServiceLayer superheroService;
    LocationServiceLayer locationService;
    
    @Inject
    public SightingController(SightingServiceLayer sightingService, SuperheroServiceLayer superheroService, LocationServiceLayer locationService){
        this.sightingService = sightingService;
        this.superheroService = superheroService;
        this.locationService = locationService;
    }
    
    @RequestMapping(value="/displaySightingDetails", method=RequestMethod.GET)
    public String displaySightings(HttpServletRequest request, Model model){
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);
        Sighting sighting = sightingService.getSightingById(sightingId);
        Superhero superhero = superheroService.getSuperheroById(sighting.getHeroId());
        Location location = locationService.getLocationById(sighting.getLocationId());
        model.addAttribute("sighting", sighting);
        model.addAttribute("superhero", superhero);
        model.addAttribute("location", location);
        return "sightingDetails";
    }
    
    @RequestMapping(value="/createSighting", method=RequestMethod.POST)
    public String createSuperhero(HttpServletRequest request){
        Sighting sighting = new Sighting();
        sighting.setHeroId(Integer.parseInt(request.getParameter("heroId")));
        sighting.setLocationId(Integer.parseInt(request.getParameter("locationId")));
        sighting.setSightingTime(LocalDate.parse(request.getParameter("sightingTime"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        sightingService.addSighting(sighting);
        
        return "redirect:displaySightingsPage";
    }
    
    @RequestMapping(value="/displaySightingsPage", method=RequestMethod.GET)
    public String displaySightingsPage(Model model){
        List<Sighting> sightingList = sightingService.getAllSightings();
        model.addAttribute("sightingList", sightingList);
        
        List<Superhero> superheroList = new ArrayList<Superhero>();
        List<Location> locationList = new ArrayList<Location>();
        List<LocalDate> sightingTimeList = new ArrayList<LocalDate>();
        
       for (int i=0; i<sightingList.size();i++){
           Sighting sighting = sightingList.get(i);
            superheroList.add(superheroService.getSuperheroById(sighting.getHeroId()));
            locationList.add(locationService.getLocationById(sighting.getLocationId()));
            sightingTimeList.add(sightingList.get(i).getSightingTime());
        }
        model.addAttribute("superheroList", superheroList);
        model.addAttribute("locationList", locationList);
        model.addAttribute("sightingTimeList", sightingTimeList); 
        return "sightings";
    }
    
    @RequestMapping(value="/deleteSighting", method=RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request){
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);
        sightingService.deleteSighting(sightingId);
        return "redirect:displaySightingsPage";
    }
    
    @RequestMapping(value="/displayEditSightingForm",method=RequestMethod.GET)
    public String displayEditSightingForm(HttpServletRequest request,Model model){
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);
        Sighting sighting = sightingService.getSightingById(sightingId);
        model.addAttribute("sighting", sighting);
        return "editSightingForm";
    }
    
    @RequestMapping(value="/editSighting", method=RequestMethod.POST)
    public String editSighting(@Valid @ModelAttribute("sighting") Sighting sighting, BindingResult result){
      if (result.hasErrors()){
          return "editSightingForm";
      }
       sightingService.updateSighting(sighting);
       
       return "redirect:displaySightingsPage";
    }
   
   
    @RequestMapping(value="/searchSightings", method=RequestMethod.GET)
    public String getSightingSearchResults(HttpServletRequest request, Model model){
        LocalDate sightingDate = LocalDate.parse(request.getParameter("sightingTime"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Sighting> sightingList = sightingService.getSightingsByDate(sightingDate);
        model.addAttribute("sightingList", sightingList);
        
        List<Superhero> superheroList = new ArrayList<Superhero>();
        List<Location> locationList = new ArrayList<Location>();
        List<LocalDate> sightingTimeList = new ArrayList<LocalDate>();
        
       for (int i=0; i<sightingList.size();i++){
           Sighting sighting = sightingList.get(i);
            superheroList.add(superheroService.getSuperheroById(sighting.getHeroId()));
            locationList.add(locationService.getLocationById(sighting.getLocationId()));
            sightingTimeList.add(sightingList.get(i).getSightingTime());
        }
        model.addAttribute("superheroList", superheroList);
        model.addAttribute("locationList", locationList);
        model.addAttribute("sightingTimeList", sightingTimeList); 
        model.addAttribute("sightingTime", sightingDate);
        return "sightingSearchResults";
    }

}