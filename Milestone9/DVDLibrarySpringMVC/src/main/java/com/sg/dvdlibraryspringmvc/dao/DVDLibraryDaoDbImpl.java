/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.DVD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import javax.inject.Inject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public class DVDLibraryDaoDbImpl implements DVDLibraryDao {
    public static final String SQL_INSERT_DVD="insert into dvd_list (dvd_title,release_year,director,rating,notes) values (?,?,?,?,?)";
    public static final String SQL_SELECT_DVD="select * from dvd_list where dvd_id=?";
    public static final String SQL_SELECT_ALL_DVDS="select * from dvd_list";
    public static final String SQL_DELETE_DVD="delete from dvd_list where dvd_id=?";
    public static final String SQL_UPDATE_DVD="update dvd_list set dvd_title =?,release_year=?,director=?,rating=?,notes=? where dvd_id=?";
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public DVD addDvd(DVD dvd) {
        jdbcTemplate.update(SQL_INSERT_DVD, dvd.getDvdTitle(), dvd.getReleaseYear(), dvd.getDirector(), dvd.getRating(), dvd.getNotes());
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        dvd.setDvdId(newId);
        return dvd;
        
    }
   
    @Override
    public DVD getDvdById(Integer dvdId) {
        try{
            return jdbcTemplate.queryForObject(SQL_SELECT_DVD, new DVDMapper(), dvdId);
        }
        catch(EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public void updateDvd(DVD dvd) {
        jdbcTemplate.update(SQL_UPDATE_DVD, dvd.getDvdTitle(), dvd.getReleaseYear(), dvd.getDirector(), dvd.getRating(), dvd.getNotes(), dvd.getDvdId());
    }

    @Override
    public void removeDvd(Integer dvdId) {
        jdbcTemplate.update(SQL_DELETE_DVD,dvdId);
    }

    @Override
    public List<DVD> getAllDvds() {
        return jdbcTemplate.query(SQL_SELECT_ALL_DVDS, new DVDMapper());
    }

    @Override
    public List<DVD> searchDvds(Map<SearchTerm, String> criteria) {
        String titleSearchCriteria = criteria.get(SearchTerm.TITLE);
        String directorSearchCriteria = criteria.get(SearchTerm.DIRECTOR);
        String ratingSearchCriteria = criteria.get(SearchTerm.RATING);
        String releaseYearSearchCriteria = criteria.get(SearchTerm.YEAR);
        
        Predicate <DVD> titleMatchCriteria;
        Predicate <DVD> directorMatchCriteria;
        Predicate <DVD> ratingMatchCriteria;
        Predicate <DVD> releaseYearMatchCriteria;
        
        if (criteria.isEmpty()){
            return getAllDvds();
        }
        else{
            StringBuilder sQuery = new StringBuilder("select * from dvds where ");
            int numParams = criteria.size();
            int paramPosition = 0;
            String[] paramVals = new String[numParams];
            Set<SearchTerm> keySet = criteria.keySet();
            Iterator<SearchTerm> iter = keySet.iterator();
            
            while (iter.hasNext()){
                SearchTerm currentKey = iter.next();
                if (paramPosition > 0 ){
                    sQuery.append(" and ");
                }
                sQuery.append(currentKey);
                sQuery.append(" =? ");
                paramVals[paramPosition] = criteria.get(currentKey);
                paramPosition++;
            }
            
            
            return jdbcTemplate.query(sQuery.toString(), new DVDMapper(), paramVals);
    }
    }
    
    public static final class DVDMapper implements RowMapper<DVD>{

        @Override
        public DVD mapRow(ResultSet rs, int i) throws SQLException {
            DVD dvd = new DVD();
            dvd.setDvdId(rs.getInt("dvd_id"));
            dvd.setDirector(rs.getString("director"));
            dvd.setDvdTitle(rs.getString("dvd_title"));
            dvd.setNotes(rs.getString("notes"));
            dvd.setReleaseYear(rs.getString("release_year"));
            dvd.setRating(rs.getString("rating"));
            return dvd;
        }
        
    }

}
