package com.sg.classroster;

import com.sg.classroster.controller.*;
import com.sg.classroster.ui.*;
import com.sg.classroster.dao.*;
import com.sg.classroster.dto.*;

public class App {

    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        ClassRosterView myView = new ClassRosterView(myIo);
        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
        ClassRosterController controller = new ClassRosterController(myDao, myView);
        controller.run();
    }
}
