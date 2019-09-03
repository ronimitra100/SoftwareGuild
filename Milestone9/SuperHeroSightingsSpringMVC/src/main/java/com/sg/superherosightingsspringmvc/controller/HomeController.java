package com.sg.superherosightingsspringmvc.controller;

import com.sg.superherosightingsspringmvc.service.*;
import com.sg.superherosightingsspringmvc.model.*;
import com.sg.superherosightingsspringmvc.dao.*;
import java.time.LocalDate;
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
public class HomeController {
    SightingServiceLayer sightingService;
    SuperheroServiceLayer superheroService;
    LocationServiceLayer locationService;
    
    @Inject
    public HomeController(SightingServiceLayer sightingService, SuperheroServiceLayer superheroService, LocationServiceLayer locationService){
        this.sightingService = sightingService;
        this.superheroService = superheroService;
        this.locationService = locationService;
    }
    
     @RequestMapping(value="/", method=RequestMethod.GET)
    public String displaySightingsOnHomePage(Model model){
        List<Sighting> sightingList = sightingService.getRecentSightings();
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
        return "home";
    }
    

}
