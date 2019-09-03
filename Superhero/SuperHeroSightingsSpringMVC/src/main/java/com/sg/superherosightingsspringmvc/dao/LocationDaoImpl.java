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


public class LocationDaoImpl implements LocationDao{
    
    private JdbcTemplate jdbcTemplate;
    
    /*=======================================================================================
     * PREPARED STATEMENTS RELATED TO VARIOUS CRUD/FILTERING OPERATIONS FOR LOCATIONS
     *=======================================================================================
     */
    public static final String SQL_INSERT_LOCATION = "insert into locations (location_name,location_description,location_address, latitude,longitude) values (?,?,?,?,?)";
    public static final String SQL_SELECT_LOCATION = "select * from locations where location_id=?";
    public static final String SQL_UPDATE_LOCATION = "update locations set location_name=?,location_description=?,location_address=?, latitude=?,longitude=? where location_id=?";
    public static final String SQL_DELETE_LOCATION = "delete from locations where location_id=?";
    public static final String SQL_SELECT_ALL_LOCATIONS = "select * from locations";
    public static final String SQL_SELECT_ALL_LOCATIONS_BY_HERO_ID = "select l.location_id,l.location_name,l.location_description,l.location_address, l.latitude,l.longitude from locations l join sightings s on l.location_id=s.location_id where s.hero_id=?";

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*=======================================================================================
     * METHODS RELATED TO VARIOUS CRUD/FILTERING OPERATIONS FOR LOCATIONS
     *=======================================================================================
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION, location.getLocationName(), location.getLocationDescription(), location.getLocationAddress(), location.getLatitude(), location.getLongitude());
        int locationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        location.setLocationId(locationId);
    }

    @Override
    public Location getLocationById(int locationId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION, new LocationMapper(), locationId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION, location.getLocationName(), location.getLocationDescription(), location.getLocationAddress(), location.getLatitude(), location.getLongitude(), location.getLocationId());
    }

    @Override
    public void deleteLocationById(int locationId) {
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationId);
    }

    @Override
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    public List<Location> getLocationsVisitedBySuperhero(int superheroId) {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS_BY_HERO_ID, new LocationMapper(), superheroId);
    }

    
    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location l = new Location();
            l.setLocationId(rs.getInt("location_id"));
            l.setLocationName(rs.getString("location_name"));
            l.setLocationDescription(rs.getString("location_description"));
            l.setLocationAddress(rs.getString("location_address"));
            l.setLatitude(rs.getDouble("latitude"));
            l.setLongitude(rs.getDouble("longitude"));

            return l;
        }

    }

}
