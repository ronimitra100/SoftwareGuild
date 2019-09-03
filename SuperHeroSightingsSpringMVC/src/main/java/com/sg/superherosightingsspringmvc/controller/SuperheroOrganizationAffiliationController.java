package com.sg.superherosightingsspringmvc.controller;

import com.sg.superherosightingsspringmvc.service.*;
import com.sg.superherosightingsspringmvc.model.*;
import com.sg.superherosightingsspringmvc.dao.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class SuperheroOrganizationAffiliationController {
    SuperheroOrganizationAffiliationServiceLayer affiliationService;
    
    @Inject
    public SuperheroOrganizationAffiliationController(SuperheroOrganizationAffiliationServiceLayer affiliationService){
        this.affiliationService = affiliationService;
    }
    
    @RequestMapping(value="/displayAffiliationDetails", method=RequestMethod.GET)
    public String displayAffiliations(HttpServletRequest request, Model model){
        String affiliationIdParameter = request.getParameter("affiliationId");
        int affiliationId = Integer.parseInt(affiliationIdParameter);
        SuperheroOrganizationAffiliation affiliation = affiliationService.getSuperheroOrganizationAffiliation(affiliationId);
        model.addAttribute("affiliation", affiliation);
        return "superheroOrganizationAffiliationDetails";
    }
    
    @RequestMapping(value="/createAffiliation", method=RequestMethod.POST)
    public String createAffiliation(HttpServletRequest request){
        SuperheroOrganizationAffiliation affiliation = new SuperheroOrganizationAffiliation();
        affiliation.setHeroId(Integer.parseInt(request.getParameter("heroId")));
        affiliation.setOrgId(Integer.parseInt(request.getParameter("orgId")));
        
        affiliationService.addSuperheroOrganizationAffiliation(affiliation);
        
        return "redirect:displaySuperheroOrganizationAffiliationsPage";
    }
    
    @RequestMapping(value="/displayAffiliationsPage", method=RequestMethod.GET)
    public String displayAffiliationsPage(Model model){
        List<SuperheroOrganizationAffiliation> affiliationList = affiliationService.getAllSuperheroOrganizationAffiliations();
        model.addAttribute("affiliation", affiliationList);
        return "superheroOrganizationAffiliations";
    }
    
    @RequestMapping(value="/deleteAffiliation", method=RequestMethod.GET)
    public String deleteSuperheroOrganizationAffiliation(HttpServletRequest request){
        String affiliationIdParameter = request.getParameter("affiliationId");
        int affiliationId = Integer.parseInt(affiliationIdParameter);
        affiliationService.deleteSuperheroOrganizationAffiliation(affiliationId);
        return "redirect:displaySuperheroOrganizationAffiliationsPage";
    }
    
    @RequestMapping(value="/displayEditAffiliationForm",method=RequestMethod.GET)
    public String displayEditAffiliationForm(HttpServletRequest request,Model model){
        String affiliationIdParameter = request.getParameter("affiliationId");
        int affiliationId = Integer.parseInt(affiliationIdParameter);
        SuperheroOrganizationAffiliation affiliation = affiliationService.getSuperheroOrganizationAffiliation(affiliationId);
        model.addAttribute("affiliation", affiliation);
        return "editAffiliationForm";
    }
    
    @RequestMapping(value="/editAffiliation", method=RequestMethod.POST)
    public String editAffiliation(@Valid @ModelAttribute("affiliation") SuperheroOrganizationAffiliation affiliation, BindingResult result){
      if (result.hasErrors()){
          return "editSightingForm";
      }
       affiliationService.updateSuperheroOrganizationAffiliation(affiliation);
       
       return "redirect:displaySightingsPage";
    }

}

