/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sg.dvdlibraryspringmvc.dao;
import com.sg.dvdlibraryspringmvc.model.*;
import java.util.List;
import java.util.Map;


public interface DVDLibraryDao {
    public DVD addDvd(DVD dvd);
    public DVD getDvdById(Integer dvdId);
    public void updateDvd(DVD dvd);
    public void removeDvd(Integer dvdId);
    public List<DVD> getAllDvds();
    public List<DVD> searchDvds(Map<SearchTerm,String> criteria);
}
