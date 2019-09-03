package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.*;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.time.*;


public class OrganizationDaoImpl implements OrganizationDao{
    
    private JdbcTemplate jdbcTemplate;
    
    /*=======================================================================================
     * PREPARED STATEMENTS RELATED TO VARIOUS CRUD/FILTERING OPERATIONS FOR ORGANIZATIONS
     *=======================================================================================
     */
    public static final String SQL_INSERT_ORGANIZATION = "insert into organizations (org_name, org_description, org_address) values (?,?,?)";
    public static final String SQL_SELECT_ORGANIZATION = "select * from organizations where org_id=?";
    public static final String SQL_UPDATE_ORGANIZATION = "update organizations set org_name=?, org_description=?, org_address=? where org_id=?";
    public static final String SQL_DELETE_ORGANIZATION = "delete from organizations where org_id=?";
    public static final String SQL_SELECT_ALL_ORGANIZATIONS = "select * from organizations";
    public static final String SQL_SELECT_ALL_ORGANIZATIONS_BY_HERO_ID = "select o.org_id, o.org_name, o.org_description, o.org_address from organizations o join superhero_organization_affiliations a on a.org_id=o.org_id where a.hero_id=?";

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*=======================================================================================
     * METHODS RELATED TO VARIOUS CRUD/FILTERING OPERATIONS FOR ORGANIZATIONS
     *=======================================================================================
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrganization(Organization org) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION, org.getOrgName(), org.getOrgDescription(), org.getOrgAddress());
        int orgId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        org.setOrgId(orgId);
    }

    @Override
    public Organization getOrgnizationById(int orgId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION, new OrganizationMapper(), orgId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Override
    public void updateOrganization(Organization org) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION, org.getOrgName(), org.getOrgDescription(), org.getOrgAddress(), org.getOrgId());
    }

    @Override
    public void deleteOrganizationById(int orgId) {
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, orgId);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
    }

    @Override
    public List<Organization> getOrganizationsAffliatedWithSuperhero(int superheroId) {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS_BY_HERO_ID, new OrganizationMapper(), superheroId);
    }

    
    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization o = new Organization();
            o.setOrgId(rs.getInt("org_id"));
            o.setOrgName(rs.getString("org_name"));
            o.setOrgDescription(rs.getString("org_description"));
            o.setOrgAddress(rs.getString("org_address"));

            return o;
        }

    }

}
