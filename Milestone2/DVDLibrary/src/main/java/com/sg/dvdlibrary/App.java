package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.*;
import com.sg.dvdlibrary.dao.*;
import com.sg.dvdlibrary.dto.*;
import com.sg.dvdlibrary.ui.*;

public class App {

    public static void main(String[] args) {
        UserIO myIO = new UserIOConsoleImpl();
        DVDLibraryView myView = new DVDLibraryView(myIO);
        DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
        DVDLibraryController controller = new DVDLibraryController(myView, myDao);
        controller.run();

    }
}
