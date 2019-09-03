package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.model.*;
import com.sg.superherosightingsspringmvc.dao.*;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;


public class OrganizationServiceLayerImpl implements OrganizationServiceLayer{
    OrganizationDao organizationDao;
    
    @Inject
    public OrganizationServiceLayerImpl(OrganizationDao organizationDao){
        this.organizationDao = organizationDao;
    }

    @Override
    public void addOrganization(Organization org) {
        organizationDao.addOrganization(org);
    }

    @Override
    public Organization getOrgnizationById(int orgId) {
        return organizationDao.getOrgnizationById(orgId);
    }

    @Override
    public void updateOrganization(Organization org) {
        organizationDao.updateOrganization(org);
    }

    @Override
    public void deleteOrganizationById(int orgId) {
        organizationDao.deleteOrganizationById(orgId);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationDao.getAllOrganizations();
    }

    @Override
    public List<Organization> getOrganizationsAffliatedWithSuperhero(int superheroId) {
        return organizationDao.getOrganizationsAffliatedWithSuperhero(superheroId);
    }

}
