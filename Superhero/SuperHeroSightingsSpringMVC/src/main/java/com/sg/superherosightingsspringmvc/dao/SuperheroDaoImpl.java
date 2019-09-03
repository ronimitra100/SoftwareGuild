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


public class SuperheroDaoImpl implements SuperheroDao{
    
    private JdbcTemplate jdbcTemplate;
    
    /*=======================================================================================
     * PREPARED STATEMENTS RELATED TO VARIOUS CRUD/FILTERING OPERATIONS FOR SUPERHEROES
     *=======================================================================================
     */
    public static final String SQL_INSERT_SUPERHERO = "insert into superheroes (hero_name, hero_description, hero_superpower) values (?,?,?)";
    public static final String SQL_SELECT_SUPERHERO = "select * from superheroes where hero_id=?";
    public static final String SQL_UPDATE_SUPERHERO = "update superheroes set hero_name=?, hero_description=?, hero_superpower=? where hero_id=?";
    public static final String SQL_DELETE_SUPERHERO = "delete from superheroes where hero_id=?";
    public static final String SQL_SELECT_ALL_SUPERHEROES = "select * from superheroes";
    public static final String SQL_SELECT_ALL_SUPERHEROES_BY_LOCATION_ID = "select h.hero_id, h.hero_name, h.hero_description, h.hero_superpower from superheroes h join sightings s on h.hero_id=s.hero_id where s.location_id=?";
    public static final String SQL_SELECT_ALL_SUPERHEROES_BY_ORG_ID = "select h.hero_id, h.hero_name, h.hero_description, h.hero_superpower from superheroes h join superhero_organization_affiliations a on h.hero_id=a.hero_id where a.org_id=?";

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*=======================================================================================
     * METHODS RELATED TO VARIOUS CRUD/FILTERING OPERATIONS FOR SUPERHEROES
     *=======================================================================================
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSuperhero(Superhero superhero) {
        jdbcTemplate.update(SQL_INSERT_SUPERHERO,
                superhero.getHeroName(),
                superhero.getHeroDescription(),
                superhero.getHeroSuperPower()
        );
        int heroId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        superhero.setHeroId(heroId);
    }

    @Override
    public Superhero getSuperheroById(int superheroId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SUPERHERO, new SuperheroMapper(), superheroId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Override
    public void updateSuperhero(Superhero superhero) {
        jdbcTemplate.update(SQL_UPDATE_SUPERHERO, superhero.getHeroName(), superhero.getHeroDescription(), superhero.getHeroSuperPower(), superhero.getHeroId());
    }

    @Override
    public void deleteSuperheroById(int superheroId) {
        jdbcTemplate.update(SQL_DELETE_SUPERHERO, superheroId);
    }

    @Override
    public List<Superhero> getAllSuperheroes() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SUPERHEROES, new SuperheroMapper());
    }

    @Override
    public List<Superhero> getSuperheroesSpottedAtSightingLocation(int locationId) {
        List<Superhero> heroList = jdbcTemplate.query(SQL_SELECT_ALL_SUPERHEROES_BY_LOCATION_ID, new SuperheroMapper(), locationId);
        return heroList;
    }

    @Override
    public List<Superhero> getSuperheroesAffiliatedWithOrganization(int orgId) {
        List<Superhero> heroList = jdbcTemplate.query(SQL_SELECT_ALL_SUPERHEROES_BY_ORG_ID, new SuperheroMapper(), orgId);
        return heroList;
    }
    /*=======================================================================================
     * ROWMAPPER CLASS CORRESPONDING TO "SUPERHEROES" TABLE
     *=======================================================================================
     */
    public static final class SuperheroMapper implements RowMapper<Superhero> {

        @Override
        public Superhero mapRow(ResultSet rs, int i) throws SQLException {
            Superhero h = new Superhero();
            h.setHeroId(rs.getInt("hero_id"));
            h.setHeroName(rs.getString("hero_name"));
            h.setHeroDescription(rs.getString("hero_description"));
            h.setHeroSuperPower(rs.getString("hero_superpower"));

            return h;
        }
    }

}
