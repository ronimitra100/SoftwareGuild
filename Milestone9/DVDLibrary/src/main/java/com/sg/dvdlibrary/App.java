package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.*;
import com.sg.dvdlibrary.dao.*;
import com.sg.dvdlibrary.dto.*;
import com.sg.dvdlibrary.ui.*;
import java.sql.Time;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Date;




public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        DVDLibraryController controller = ctx.getBean("controller", DVDLibraryController.class);
        controller.run();
 

    }
}
