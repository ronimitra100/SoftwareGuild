package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.*;
import com.sg.dvdlibrary.dto.*;
import com.sg.dvdlibrary.ui.*;
import java.util.List;

public class DVDLibraryController {

    private UserIO io = new UserIOConsoleImpl();
    DVDLibraryView view;
    DVDLibraryDao dao;

    public DVDLibraryController(DVDLibraryView view, DVDLibraryDao dao) {
        this.view = view;
        this.dao = dao;
    }

    public void run() {
        boolean keepgoing = true;
        int menuSelection = 0;

        try {
            while (keepgoing) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        addDVD();
                        break;
                    case 2:
                        removeDVD();
                        break;
                    case 3:
                        updateDVD();
                        break;
                    case 4:
                        listDVDs();
                        break;
                    case 5:
                        fetchDVD();
                        break;
                    case 6:
                        listMatchingDVDs();
                        break;
                    case 7:
                        keepgoing = false;
                        exitMessage();
                        break;
                }
            }
        } catch (DVDLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    public void addDVD() throws DVDLibraryDaoException {
        view.displayAddDVDBanner();
        DVDLibrary dvd = view.getNewDVDInfo();
        dao.addDVD(dvd.getTitle(), dvd);
        view.displayDVDSuccessfullyAddedBanner();
    }

    public void removeDVD() throws DVDLibraryDaoException {
        view.displayRemoveDVDBanner();
        String titleOfDvdToBeRemoved = view.getDVDToBeRemoved();
        dao.removeDVD(titleOfDvdToBeRemoved);
        view.displayDVDSuccessfullyRemovedBanner();
    }

    public void updateDVD() throws DVDLibraryDaoException {
        view.displayUpdateDVDInfoBanner();
        String titleOfDvdToBeUpdated = view.getDVDToBeUpdated();

        view.displayCurrentInfoForParticularDVD();
        view.displayInfoAboutAGivenDVD(dao.getDVDDetails(titleOfDvdToBeUpdated));

        DVDLibrary dvd = view.getNewDVDInfo();
        dao.addDVD(titleOfDvdToBeUpdated, dvd);

        view.displayCurrentInfoForParticularDVD();
        view.displayInfoAboutAGivenDVD(dao.getDVDDetails(titleOfDvdToBeUpdated));

        view.displayDVDSuccessfullyUpdatedBanner();
    }

    public void fetchDVD() throws DVDLibraryDaoException {
        view.displayParticularDVDInfoBanner();
        String titleOfDvdToBeRetrieved = view.getDVDToBeRetrieved();

        view.displayParticularDVDInfoBanner();
        view.displayInfoAboutAGivenDVD(dao.getDVDDetails(titleOfDvdToBeRetrieved));
        view.displayDVDSuccessfullyFetchedBanner();
    }

    public void listDVDs() throws DVDLibraryDaoException {
        view.displayListOfAllDVDsBanner();
        view.displayListOfAllDVDs(dao.getListOfAllDVDs());
        view.displayAllDVDsSuccessfullyRetrievedBanner();
    }

    public void listMatchingDVDs() throws DVDLibraryDaoException {
        view.displaySearchDVDBanner();
        String keyword = view.getDVDSearchKeyword();

        view.displayListOfMatchingDVDsBanner();
        view.displayDVDSearchResults(keyword, dao.getListOfAllDVDs());
        view.displayKeywordSearchWasSuccessfulBanner();
    }

    public void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    public void exitMessage() {
        view.displayExitMessage();
    }
}
