package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.model.*;
import com.sg.superherosightingsspringmvc.dao.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;


public class SuperheroOrganizationAffiliationServiceLayerImpl implements SuperheroOrganizationAffiliationServiceLayer{
    
    SuperheroOrganizationAffiliationDao affiliationDao;
    
    @Inject
    public SuperheroOrganizationAffiliationServiceLayerImpl(SuperheroOrganizationAffiliationDao affiliationDao){
        this.affiliationDao = affiliationDao;
    }
    
    @Override
    public void addSuperheroOrganizationAffiliation(SuperheroOrganizationAffiliation affiliation) {
        affiliationDao.addSuperheroOrganizationAffiliation(affiliation);
    }

    @Override
    public SuperheroOrganizationAffiliation getSuperheroOrganizationAffiliation(int affiliationId) {
        return affiliationDao.getSuperheroOrganizationAffiliation(affiliationId);
    }

    @Override
    public void updateSuperheroOrganizationAffiliation(SuperheroOrganizationAffiliation affiliation) {
        affiliationDao.updateSuperheroOrganizationAffiliation(affiliation);
    }

    @Override
    public void deleteSuperheroOrganizationAffiliation(int affiliationId) {
        affiliationDao.deleteSuperheroOrganizationAffiliation(affiliationId);
    }

    @Override
    public List<SuperheroOrganizationAffiliation> getAllSuperheroOrganizationAffiliations() {
        return affiliationDao.getAllSuperheroOrganizationAffiliations();
    }

    @Override
    public List<Integer> getAffiliationIdsLinkedToGivenHeroAndOrg(int heroId, int orgId) {
        return  affiliationDao.getAllSuperheroOrganizationAffiliations().stream().filter((a)-> (heroId==a.getHeroId()) && (orgId==a.getOrgId())).map(SuperheroOrganizationAffiliation::getAffiliationId).collect(Collectors.toList());
        
    }
    

}
