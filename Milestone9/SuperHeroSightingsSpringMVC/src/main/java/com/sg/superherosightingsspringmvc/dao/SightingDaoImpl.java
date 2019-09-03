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

public class SightingDaoImpl implements SightingDao{
    
    private JdbcTemplate jdbcTemplate;
    
    /*=======================================================================================
     * PREPARED STATEMENTS RELATED TO VARIOUS CRUD/FILTERING OPERATIONS FOR SIGHTINGS
     *=======================================================================================
     */
    public static final String SQL_INSERT_SIGHTING = "insert into sightings (hero_id,location_id,sighting_time) values (?,?,?)";
    public static final String SQL_SELECT_SIGHTING = "select * from sightings where sighting_id=?";
    public static final String SQL_UPDATE_SIGHTING = "update sightings set hero_id=?,location_id=?,sighting_time=? where sighting_id=?";
    public static final String SQL_DELETE_SIGHTING = "delete from sightings where sighting_id=?";
    public static final String SQL_SELECT_ALL_SIGHTINGS = "select * from sightings";
    public static final String SQL_SELECT_ALL_SIGHTINGS_BY_DATE = "select * from sightings where sighting_time=?";

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*=======================================================================================
     * METHODS RELATED TO VARIOUS CRUD/FILTERING OPERATIONS FOR SIGHTINGS
     *=======================================================================================
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING, sighting.getHeroId(), sighting.getLocationId(), sighting.getSightingTime().toString());
        int sightingId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        sighting.setSightingId(sightingId);
    }

    @Override
    public Sighting getSightingById(int sightingId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING, new SightingMapper(), sightingId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING, sighting.getHeroId(), sighting.getLocationId(), sighting.getSightingTime().toString(), sighting.getSightingId());
    }

    @Override
    public void deleteSighting(int sightingId) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingId);
    }

    @Override
    public List<Sighting> getAllSightings() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS, new SightingMapper());
    }

    @Override
    public List<Sighting> getSightingsByDate(LocalDate sightingDate) {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS_BY_DATE, new SightingMapper(), sightingDate.toString());
    }

    
    public static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting s = new Sighting();
            s.setSightingId(rs.getInt("sighting_id"));
            s.setHeroId(rs.getInt("hero_id"));
            s.setLocationId(rs.getInt("location_id"));
            s.setSightingTime(rs.getTimestamp("sighting_time").toLocalDateTime().toLocalDate());

            return s;
        }
    }

}
