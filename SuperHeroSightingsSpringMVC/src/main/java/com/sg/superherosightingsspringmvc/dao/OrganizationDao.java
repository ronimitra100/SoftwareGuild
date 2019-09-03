package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.*;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import java.time.*;

public interface OrganizationDao {
    
    public void addOrganization(Organization org);
    public Organization getOrgnizationById(int orgId);
    public void updateOrganization(Organization org);
    public void deleteOrganizationById(int orgId);
    public List<Organization> getAllOrganizations();
    public List<Organization> getOrganizationsAffliatedWithSuperhero(int superheroId);
    
}
