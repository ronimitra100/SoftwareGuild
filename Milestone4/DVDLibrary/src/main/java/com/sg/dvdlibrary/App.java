package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.*;
import com.sg.dvdlibrary.dao.*;
import com.sg.dvdlibrary.dto.*;
import com.sg.dvdlibrary.ui.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        DVDLibraryController controller = ctx.getBean("controller", DVDLibraryController.class);
        controller.run();

    }
}
