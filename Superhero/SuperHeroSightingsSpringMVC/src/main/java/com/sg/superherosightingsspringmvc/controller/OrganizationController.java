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
public class OrganizationController {
    SuperheroServiceLayer superheroService;
    OrganizationServiceLayer organizationService;
    LocationServiceLayer locationService;
    SightingServiceLayer sightingService;
    SuperheroOrganizationAffiliationServiceLayer affiliationService;
    
    @Inject
    public OrganizationController(SuperheroServiceLayer superheroService, OrganizationServiceLayer organizationService, LocationServiceLayer locationService, SightingServiceLayer sightingService, SuperheroOrganizationAffiliationServiceLayer affiliationService){
        this.superheroService = superheroService;
        this.organizationService = organizationService;
        this.locationService = locationService;
        this.sightingService = sightingService;
        this.affiliationService = affiliationService;
    }
    
    @RequestMapping(value="/displayOrganizationDetails", method=RequestMethod.GET)
    public String displayOrganizations(HttpServletRequest request, Model model){
        String orgIdParameter = request.getParameter("orgId");
        int orgId = Integer.parseInt(orgIdParameter);
        Organization org = organizationService.getOrgnizationById(orgId);
        model.addAttribute("organization", org);
        List<Superhero> superheroList = superheroService.getSuperheroesAffiliatedWithOrganization(orgId);
        superheroList.sort(Comparator.comparing(Superhero::getHeroName));
        model.addAttribute("superheroList", superheroList);
        
        List<Superhero> allHeroes = superheroService.getAllSuperheroes();
        allHeroes.sort(Comparator.comparing(Superhero::getHeroName));
         model.addAttribute("allSuperheroes", allHeroes);
        return "organizationDetails";
    }
    
    @RequestMapping(value="/createOrganization", method=RequestMethod.POST)
    public String createOrganization(HttpServletRequest request){
        Organization org = new Organization();
        org.setOrgName(request.getParameter("orgName"));
        org.setOrgDescription(request.getParameter("orgDescription"));
        org.setOrgAddress(request.getParameter("orgAddress"));
        organizationService.addOrganization(org);
        
        return "redirect:displayOrganizationsPage";
    }
    
    @RequestMapping(value="/displayOrganizationsPage", method=RequestMethod.GET)
    public String displayOrganizationsPage(Model model){
        List<Organization> orgList = organizationService.getAllOrganizations();
        orgList.sort(Comparator.comparing(Organization::getOrgName));
        model.addAttribute("organizationList", orgList);
       
        return "organizations";
    }
    
    @RequestMapping(value="/deleteOrganization", method=RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request){
        String orgIdParameter = request.getParameter("orgId");
        int orgId = Integer.parseInt(orgIdParameter);
        organizationService.deleteOrganizationById(orgId);
        return "redirect:displayOrganizationsPage";
    }
    
    @RequestMapping(value="/displayEditOrganizationForm",method=RequestMethod.GET)
    public String displayEditOrganizationForm(HttpServletRequest request,Model model){
        String orgIdParameter = request.getParameter("orgId");
        int orgId = Integer.parseInt(orgIdParameter);
        Organization org = organizationService.getOrgnizationById(orgId);
        model.addAttribute("organization", org);
        return "editOrganizationForm";
    }
    
    @RequestMapping(value="/editOrganization", method=RequestMethod.POST)
    public String editSuperhero(@Valid @ModelAttribute("organization") Organization org, BindingResult result){
      if (result.hasErrors()){
          return "editOrganizationForm";
      }
       organizationService.updateOrganization(org);
       
       return "redirect:displayOrganizationsPage";
    }
    
    @RequestMapping(value="/addMemberToOrg", method=RequestMethod.POST)
    public String addMemberToOrganization(HttpServletRequest request,Model model){
        int orgId = Integer.parseInt(request.getParameter("orgId"));
        int heroID = Integer.parseInt(request.getParameter("heroId"));
        Organization org = organizationService.getOrgnizationById(orgId);
        
        SuperheroOrganizationAffiliation affiliation = new SuperheroOrganizationAffiliation();
        affiliation.setOrgId(orgId);
        affiliation.setHeroId(heroID);
             
        affiliationService.addSuperheroOrganizationAffiliation(affiliation);
        model.addAttribute("organization", org);
        model.addAttribute("orgId", org.getOrgId());
        //model.addAttribute("heroId", heroID);
        
        List<Superhero> superheroList = superheroService.getSuperheroesAffiliatedWithOrganization(orgId);
        superheroList.sort(Comparator.comparing(Superhero::getHeroName));
        
        model.addAttribute("superheroList", superheroList);
        
        return "redirect:/displayOrganizationDetails?orgId={orgId}";
        //return "redirect:/displayOrganizations";
    }
    
    @RequestMapping(value="/deleteMemberFromOrg", method=RequestMethod.POST)
    public String deleteMemberFromOrganization(HttpServletRequest request,Model model){
        int orgId = Integer.parseInt(request.getParameter("orgId"));
        int heroId = Integer.parseInt(request.getParameter("heroId"));
        Organization org = organizationService.getOrgnizationById(orgId);
        

             
        List<Integer> listOfAffiliationIdsToBeDeleted = affiliationService.getAffiliationIdsLinkedToGivenHeroAndOrg(heroId, orgId);
        for (int affiliationId : listOfAffiliationIdsToBeDeleted){
            affiliationService.deleteSuperheroOrganizationAffiliation(affiliationId);
        }
        model.addAttribute("organization", org);
        model.addAttribute("orgId", org.getOrgId());
        List<Superhero> superheroList = superheroService.getSuperheroesAffiliatedWithOrganization(orgId);
        superheroList.sort(Comparator.comparing(Superhero::getHeroName));
        model.addAttribute("superheroList", superheroList);
        
        return "redirect:/displayOrganizationDetails?orgId={orgId}";
    }

}