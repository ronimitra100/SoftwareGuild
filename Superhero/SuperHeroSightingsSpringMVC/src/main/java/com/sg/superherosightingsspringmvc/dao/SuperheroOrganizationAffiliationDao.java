package com.sg.superherosightingsspringmvc.dao;
import com.sg.superherosightingsspringmvc.model.*;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import java.time.*;

public interface SuperheroOrganizationAffiliationDao {
    
    public void addSuperheroOrganizationAffiliation(SuperheroOrganizationAffiliation affiliation);
    public SuperheroOrganizationAffiliation getSuperheroOrganizationAffiliation(int affiliationId);
    public void updateSuperheroOrganizationAffiliation(SuperheroOrganizationAffiliation affiliation);
    public void deleteSuperheroOrganizationAffiliation(int affiliationId);
    public List<SuperheroOrganizationAffiliation> getAllSuperheroOrganizationAffiliations();
}
