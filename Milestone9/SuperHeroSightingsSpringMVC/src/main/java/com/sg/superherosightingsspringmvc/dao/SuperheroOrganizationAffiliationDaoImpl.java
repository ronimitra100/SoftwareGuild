package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.SuperheroOrganizationAffiliation;
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


public class SuperheroOrganizationAffiliationDaoImpl implements SuperheroOrganizationAffiliationDao{
    private JdbcTemplate jdbcTemplate;
    
    /*=======================================================================================
     * PREPARED STATEMENTS RELATED TO VARIOUS CRUD/FILTERING OPERATIONS FOR SUPERHERO-ORGANIZATION AFFILIATIONS
     *=======================================================================================
     */
    public static final String SQL_INSERT_AFFILIATION = "insert into superhero_organization_affiliations (hero_id, org_id) values (?,?)";
    public static final String SQL_SELECT_AFFILIATION = "select * from superhero_organization_affiliations where affiliation_id=?";
    public static final String SQL_UPDATE_AFFILIATION = "update superhero_organization_affiliations set hero_id=?, org_id=? where affiliation_id=?";
    public static final String SQL_DELETE_AFFILIATION = "delete from superhero_organization_affiliations where affiliation_id=?";
    public static final String SQL_SELECT_ALL_AFFILIATIONS = "select * from superhero_organization_affiliations";

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*=======================================================================================
     * METHODS RELATED TO VARIOUS CRUD OPERATIONS FOR SUPERHERO-ORGANIZATION AFFILIATIONS
     *=======================================================================================
     */

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSuperheroOrganizationAffiliation(SuperheroOrganizationAffiliation affiliation) {
        jdbcTemplate.update(SQL_INSERT_AFFILIATION, affiliation.getHeroId(), affiliation.getOrgId());
        int affiliationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        affiliation.setAffiliationId(affiliationId);
    }

    @Override
    public SuperheroOrganizationAffiliation getSuperheroOrganizationAffiliation(int affiliationId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_AFFILIATION, new AffiliationMapper(), affiliationId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void updateSuperheroOrganizationAffiliation(SuperheroOrganizationAffiliation affiliation) {
        jdbcTemplate.update(SQL_UPDATE_AFFILIATION, affiliation.getHeroId(), affiliation.getOrgId(), affiliation.getAffiliationId());
    }

    @Override
    public void deleteSuperheroOrganizationAffiliation(int affiliationId) {
        jdbcTemplate.update(SQL_DELETE_AFFILIATION, affiliationId);
    }

    @Override
    public List<SuperheroOrganizationAffiliation> getAllSuperheroOrganizationAffiliations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_AFFILIATIONS, new AffiliationMapper());
    }

    public static final class AffiliationMapper implements RowMapper<SuperheroOrganizationAffiliation> {

        @Override
        public SuperheroOrganizationAffiliation mapRow(ResultSet rs, int i) throws SQLException {
            SuperheroOrganizationAffiliation a = new SuperheroOrganizationAffiliation();
            a.setAffiliationId(rs.getInt("affiliation_id"));
            a.setHeroId(rs.getInt("hero_id"));
            a.setOrgId(rs.getInt("org_id"));

            return a;
        }
    }

}
