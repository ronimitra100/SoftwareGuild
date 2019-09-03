package com.swgcorp.flooringmastery;

import com.swgcorp.flooringmastery.configuration.*;
import com.swgcorp.flooringmastery.dto.*;
import com.swgcorp.flooringmastery.dao.*;
import com.swgcorp.flooringmastery.service.*;
import com.swgcorp.flooringmastery.controller.*;
import com.swgcorp.flooringmastery.ui.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Controller controller = ctx.getBean("controller", Controller.class);

        try {
            controller.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
