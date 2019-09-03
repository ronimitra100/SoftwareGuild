package com.sg.superherosightingsspringmvc.controller;

import com.sg.superherosightingsspringmvc.service.*;
import com.sg.superherosightingsspringmvc.model.*;
import com.sg.superherosightingsspringmvc.dao.*;
import java.util.Comparator;
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
public class LocationController {
    SuperheroServiceLayer superheroService;
    OrganizationServiceLayer organizationService;
    LocationServiceLayer locationService;
    SightingServiceLayer sightingService;
    SuperheroOrganizationAffiliationServiceLayer affiliationService;
    
    @Inject
    public LocationController(SuperheroServiceLayer superheroService, OrganizationServiceLayer organizationService, LocationServiceLayer locationService, SightingServiceLayer sightingService, SuperheroOrganizationAffiliationServiceLayer affiliationService){
        this.superheroService = superheroService;
        this.organizationService = organizationService;
        this.locationService = locationService;
        this.sightingService = sightingService;
        this.affiliationService = affiliationService;
    }
    
    @RequestMapping(value="/displayLocationDetails", method=RequestMethod.GET)
    public String displayLocations(HttpServletRequest request, Model model){
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);
        
        Location  location = locationService.getLocationById(locationId);
        model.addAttribute("location", location);
        
         
        List<Superhero> superheroList = superheroService.getSuperheroesSpottedAtSightingLocation(locationId);
        superheroList.sort(Comparator.comparing(Superhero::getHeroName));
        model.addAttribute("superheroList",superheroList);
        
        return "locationDetails";
    }
    
    @RequestMapping(value="/createLocation", method=RequestMethod.POST)
    public String createSuperhero(HttpServletRequest request){
        Location location = new Location();
        location.setLocationName(request.getParameter("locationName"));
        location.setLocationDescription(request.getParameter("locationDescription"));
        location.setLocationAddress(request.getParameter("locationAddress"));
        location.setLatitude(Double.parseDouble(request.getParameter("latitude")));
        location.setLongitude(Double.parseDouble(request.getParameter("longitude")));
        locationService.addLocation(location);
        
        return "redirect:displayLocationsPage";
    }
    
    @RequestMapping(value="/displayLocationsPage", method=RequestMethod.GET)
    public String displayLocationsPage(Model model){
        List<Location> locationList = locationService.getAllLocations();
        locationList.sort(Comparator.comparing(Location::getLocationName));
        model.addAttribute("locationList", locationList);
        return "locations";
    }
    
    @RequestMapping(value="/deleteLocation", method=RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request){
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);
        locationService.deleteLocationById(locationId);
        return "redirect:displayLocationsPage";
    }
    
    @RequestMapping(value="/displayEditLocationForm",method=RequestMethod.GET)
    public String displayEditLocationForm(HttpServletRequest request,Model model){
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);
        Location location = locationService.getLocationById(locationId);
        model.addAttribute("location", location);
        return "editLocationForm";
    }
    
    @RequestMapping(value="/editLocation", method=RequestMethod.POST)
    public String editLocation(@Valid @ModelAttribute("location") Location location, BindingResult result){
      if (result.hasErrors()){
          return "editLocationForm";
      }
       locationService.updateLocation(location);
       
       return "redirect:displayLocationsPage";
    }

}
