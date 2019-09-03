package com.sg.dvdlibrary.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import com.sg.dvdlibrary.dto.*;

public interface DVDLibraryDao {

    DVDLibrary addDVD(String title, DVDLibrary dvd) throws DVDLibraryDaoException;

    DVDLibrary removeDVD(String title) throws DVDLibraryDaoException;

    DVDLibrary updateDVD(String title, DVDLibrary dvd) throws DVDLibraryDaoException;
    
    DVDLibrary getDVDDetails(String title) throws DVDLibraryDaoException;

    ArrayList<DVDLibrary> getListOfAllDVDs() throws DVDLibraryDaoException;

    ArrayList<DVDLibrary> getMatchingDVDs(String keyword) throws DVDLibraryDaoException;
}
