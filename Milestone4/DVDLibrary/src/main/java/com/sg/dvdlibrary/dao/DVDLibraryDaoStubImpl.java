package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVDLibrary;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DVDLibraryDaoStubImpl implements DVDLibraryDao {

    DVDLibrary dvd1, dvd2, dvd3;
    ArrayList<DVDLibrary> dvdList = new ArrayList<DVDLibrary>();

    public DVDLibraryDaoStubImpl() {

        ArrayList<DVDLibrary> dvdList = new ArrayList<DVDLibrary>();
        DVDLibrary dvd1 = new DVDLibrary("Lion King");
        dvd1.setReleaseDate("1994");
        dvd1.setMpaaRating("9.75");
        dvd1.setDirector("Rob Minkoff");
        dvd1.setStudio("Disney");
        dvd1.setUserRating("9.8");
        dvdList.add(dvd1);

        DVDLibrary dvd2 = new DVDLibrary("The Kingdom of Solomon");
        dvd2.setReleaseDate("2010");
        dvd2.setMpaaRating("6.6");
        dvd2.setDirector("Shahriar Bahrani");
        dvd2.setStudio("KPS film");
        dvd2.setUserRating("9.2");
        dvdList.add(dvd2);

        DVDLibrary dvd3 = new DVDLibrary("Jurassic Park");
        dvd3.setReleaseDate("1993");
        dvd3.setMpaaRating("8.1");
        dvd3.setDirector("Steven Spielberg");
        dvd3.setStudio("Universal Studio");
        dvd3.setUserRating("8.7");
        dvdList.add(dvd3);

        this.dvd1 = dvd1;
        this.dvd2 = dvd2;
        this.dvd3 = dvd3;
        this.dvdList = dvdList;

    }

    public DVDLibrary addDVD(String title, DVDLibrary dvd) throws DVDLibraryDaoException {
        if (dvd.getTitle().equalsIgnoreCase("Lion King")) {
            return dvd1;
        } else if (dvd.getTitle().equalsIgnoreCase("The Kingdom of Solomon")) {
            return dvd2;
        } else if (dvd.getTitle().equalsIgnoreCase("Jurassic Park")) {
            return dvd3;
        } else {
            return null;
        }
    }

    public DVDLibrary removeDVD(String title) throws DVDLibraryDaoException {
        if (title.equalsIgnoreCase("Lion King")) {
            dvdList.remove(dvd1);
            return dvd1;
        } else if (title.equalsIgnoreCase("The Kingdom of Solomon")) {
            dvdList.remove(dvd2);
            return dvd2;
        } else if (title.equalsIgnoreCase("Jurassic Park")) {
            dvdList.remove(dvd3);
            return dvd3;
        } else {
            return null;
        }
    }

    public DVDLibrary updateDVD(String title, DVDLibrary dvd) throws DVDLibraryDaoException {
        if (Arrays.asList("Lion King", "The Kingdom of Solomon", "Jurassic Park").contains(title)) {
            return dvd;
        } else {
            return null;
        }
    }

    public DVDLibrary getDVDDetails(String title) throws DVDLibraryDaoException {
        if (title.equals("Lion King")) {
            return dvd1;
        } else if (title.equals("The Kingdom of Solomon")) {
            return dvd2;
        } else if (title.equals("Jurassic Park")) {
            return dvd3;
        } else {
            return null;
        }
    }

    public ArrayList<DVDLibrary> getListOfAllDVDs() throws DVDLibraryDaoException {
        return dvdList;
    }

    public ArrayList<DVDLibrary> getMatchingDVDs(String keyword) throws DVDLibraryDaoException {
        ArrayList<DVDLibrary> matchingDvds = new ArrayList<DVDLibrary>();

        for (DVDLibrary dvd : dvdList) {
            if (dvd.getTitle().contains(keyword)) {
                matchingDvds.add(dvd);
            }

        }
        return matchingDvds;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DVDLibraryDaoStubImpl other = (DVDLibraryDaoStubImpl) obj;
        if (this.dvd1 != other.dvd1 && (this.dvd1 == null || !this.dvd1.equals(other.dvd1))) {
            return false;
        }
        if (this.dvd2 != other.dvd2 && (this.dvd2 == null || !this.dvd2.equals(other.dvd2))) {
            return false;
        }
        if (this.dvd3 != other.dvd3 && (this.dvd3 == null || !this.dvd3.equals(other.dvd3))) {
            return false;
        }
        if (this.dvdList != other.dvdList && (this.dvdList == null || !this.dvdList.equals(other.dvdList))) {
            return false;
        }
        return true;
    }

}
