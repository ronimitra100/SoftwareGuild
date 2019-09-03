package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.*;
import java.util.List;
import java.util.ArrayList;

public class DVDLibraryView {

    UserIO io;

    public DVDLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add a DVD to the collection");
        io.print("2. Remove a DVD from the collection");
        io.print("3. Edit a DVD in the collection");
        io.print("4. List DVDs in the collection");
        io.print("5. Display information about a particular DVD in the collection");
        io.print("6. Search for a DVD by title");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }

    public DVDLibrary getNewDVDInfo() {
        DVDLibrary newDVD;

        String title = io.readString("Please enter DVD title");
        String releaseDate = io.readString("Please enter release date");
        String mpaaRating = io.readString("Please enter MPAA Rating");
        String director = io.readString("Please enter name of director.");
        String studio = io.readString("Please enter name of studio");
        String userRating = io.readString("Please enter user rating");

        DVDLibrary currentDVD = new DVDLibrary(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMpaaRating(mpaaRating);
        currentDVD.setDirector(director);
        currentDVD.setStudio(studio);
        currentDVD.setUserRating(userRating);

        return currentDVD;
    }

    public void displayInfoAboutAGivenDVD(DVDLibrary dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle() + ":"
                    + dvd.getReleaseDate() + ":"
                    + dvd.getMpaaRating() + ":"
                    + dvd.getDirector() + ":"
                    + dvd.getStudio() + ":"
                    + dvd.getUserRating());
        }
    }

    public void displayListOfAllDVDs(List<DVDLibrary> dvdLibrary) {
        for (DVDLibrary dvd : dvdLibrary) {
            displayInfoAboutAGivenDVD(dvd);
        }
    }

    public void displayDVDSearchResults(String keyword, List<DVDLibrary> library) {
        List<DVDLibrary> matchingDVDs = new ArrayList<DVDLibrary>();

        for (DVDLibrary dvd : library) {
            if (dvd.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                io.print(dvd.getTitle() + ":"
                        + dvd.getReleaseDate() + ":"
                        + dvd.getMpaaRating() + ":"
                        + dvd.getDirector() + ":"
                        + dvd.getStudio() + ":"
                        + dvd.getUserRating());
            }
        }

    }

    public String getDVDToBeRemoved() {
        String titleOfDvdToBeRemoved = io.readString("=== Enter title of DVDs to be removed ==="
                + "");

        return titleOfDvdToBeRemoved;
    }

    public String getDVDToBeUpdated() {
        String titleOfDvdToBeUpdated = io.readString("=== Enter title of DVD to be updated ==="
                + " ");

        return titleOfDvdToBeUpdated;
    }

    public String getDVDToBeRetrieved() {
        String titleOfDvdToBeRetrieved = io.readString("=== Enter title of DVD to be retrieved ==="
                + " ");

        return titleOfDvdToBeRetrieved;
    }

    public String getDVDSearchKeyword() {
        String keyword = io.readString("=== Enter keyword with which you want to search DVD title. "
                + "Keyword needs to be a part of the DVD title.==="
                + " ");

        return keyword;
    }

    public void displayAddDVDBanner() {
        io.print("=== Add DVD. ===");
    }

    public void displayDVDSuccessfullyAddedBanner() {
        io.print("=== DVD has been successfully added.===");
    }

    public void displayUpdateDVDInfoBanner() {
        io.print("=== Update DVD Information ===");
    }

    public void displayDVDSuccessfullyUpdatedBanner() {
        io.print("=== DVD has been successfully updated.===");
    }

    public void displayDVDSuccessfullyFetchedBanner() {
        io.print("=== DVD information has been successfully fetched.===");
    }

    public void displayRemoveDVDBanner() {
        io.print("=== Remove DVD ===");
    }

    public void displayDVDSuccessfullyRemovedBanner() {
        io.print("=== DVD has been successfully removed.===");
    }

    public void displayListOfAllDVDsBanner() {
        io.print("=== List of all DVDs ===");
    }

    public void displayListOfMatchingDVDsBanner() {
        io.print("=== List of Matching DVDs ===");
    }

    public void displayParticularDVDInfoBanner() {
        io.print("=== DVD Information ===");
    }

    public void displayCurrentInfoForParticularDVD() {
        io.print("=== Current Info for DVD ====");
    }

    public void displaySearchDVDBanner() {
        io.print("=== DVD Search Results ===");
    }

    public void displayAllDVDsSuccessfullyRetrievedBanner() {
        io.print("=== All DVDs were successfully retrieved. ===");
    }

    public void displayKeywordSearchWasSuccessfulBanner() {
        io.print("=== Matching DVD results were successfully retrieved. ===");
    }

    public void displayExitMessage() {
        io.print("Goodbye!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command");
    }

    public void displayErrorMessage(String errMsg) {
        io.print("=== Error ===");
        io.print(errMsg);
    }
}
