package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.model.*;
import com.sg.superherosightingsspringmvc.dao.*;
import java.util.List;

public interface SuperheroOrganizationAffiliationServiceLayer {
     public void addSuperheroOrganizationAffiliation(SuperheroOrganizationAffiliation affiliation);
    public SuperheroOrganizationAffiliation getSuperheroOrganizationAffiliation(int affiliationId);
    public void updateSuperheroOrganizationAffiliation(SuperheroOrganizationAffiliation affiliation);
    public void deleteSuperheroOrganizationAffiliation(int affiliationId);
    public List<SuperheroOrganizationAffiliation> getAllSuperheroOrganizationAffiliations();
    public List<Integer> getAffiliationIdsLinkedToGivenHeroAndOrg(int heroId, int orgId);
}
