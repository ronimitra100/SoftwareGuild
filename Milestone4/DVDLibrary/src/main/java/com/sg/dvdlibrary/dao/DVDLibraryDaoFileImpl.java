package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.*;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    private Map<String, DVDLibrary> dvds = new HashMap<String, DVDLibrary>();
    public static final String DVD_LIBRARY_FILE = "dvdLibrary.txt";
    public static final String DELIMITER = "::";

    @Override
    public DVDLibrary addDVD(String dvdTitle, DVDLibrary dvd) throws DVDLibraryDaoException {

        DVDLibrary newDvd = dvds.put(dvdTitle, dvd);
        writeDVDLibrary();
        return newDvd;
    }

    @Override
    public DVDLibrary removeDVD(String title) throws DVDLibraryDaoException {
        DVDLibrary removedDvd = dvds.remove(title);
        writeDVDLibrary();
        return removedDvd;
    }

    @Override
    public DVDLibrary updateDVD(String dvdTitle, DVDLibrary dvd) throws DVDLibraryDaoException {
        DVDLibrary updatedDvd = dvds.put(dvdTitle, dvd);
        writeDVDLibrary();
        return updatedDvd;
    }

    @Override
    public DVDLibrary getDVDDetails(String title) throws DVDLibraryDaoException {
        loadDVDLibrary();
        return dvds.get(title);
    }

    @Override
    public ArrayList<DVDLibrary> getListOfAllDVDs() throws DVDLibraryDaoException {
        loadDVDLibrary();
        return new ArrayList<DVDLibrary>(dvds.values());
    }

    @Override
    public ArrayList<DVDLibrary> getMatchingDVDs(String keyword) throws DVDLibraryDaoException {
        ArrayList<DVDLibrary> matchingDvds = new ArrayList<DVDLibrary>();
        for (DVDLibrary dvd : getListOfAllDVDs()) {
            if (dvd.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                matchingDvds.add(dvd);
            }
        }
        return matchingDvds;
    }

    public void loadDVDLibrary() throws DVDLibraryDaoException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(DVD_LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException("-_- Could not load DVD Library data into memory");
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            DVDLibrary currentDVD = new DVDLibrary(currentTokens[0]);
            currentDVD.setTitle(currentTokens[0]);
            currentDVD.setReleaseDate(currentTokens[1]);
            currentDVD.setMpaaRating(currentTokens[2]);
            currentDVD.setDirector(currentTokens[3]);
            currentDVD.setStudio(currentTokens[4]);
            currentDVD.setUserRating(currentTokens[5]);

            dvds.put(currentDVD.getTitle(), currentDVD);
        }
        scanner.close();
    }

    public void writeDVDLibrary() throws DVDLibraryDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_LIBRARY_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException("Could not save data in file", e);
        }

        List<DVDLibrary> dvdList = this.getListOfAllDVDs();
        System.out.println(dvdList);
        for (DVDLibrary currentDVD : dvdList) {
            out.println(currentDVD.getTitle() + DELIMITER
                    + currentDVD.getReleaseDate() + DELIMITER
                    + currentDVD.getMpaaRating() + DELIMITER
                    + currentDVD.getDirector() + DELIMITER
                    + currentDVD.getStudio() + DELIMITER
                    + currentDVD.getUserRating());
            out.flush();
        }
        out.close();
    }
}
