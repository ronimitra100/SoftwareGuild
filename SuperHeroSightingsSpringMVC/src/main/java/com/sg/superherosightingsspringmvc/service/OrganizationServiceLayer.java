package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.model.*;
import com.sg.superherosightingsspringmvc.dao.*;
import java.util.List;

public interface OrganizationServiceLayer {
    public void addOrganization(Organization org);
    public Organization getOrgnizationById(int orgId);
    public void updateOrganization(Organization org);
    public void deleteOrganizationById(int orgId);
    public List<Organization> getAllOrganizations();
    public List<Organization> getOrganizationsAffliatedWithSuperhero(int superheroId);
}
