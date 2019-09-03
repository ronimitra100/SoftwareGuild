package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.*;
import com.sg.vendingmachine.ui.*;
import com.sg.vendingmachine.dao.*;
import com.sg.vendingmachine.dto.*;
import com.sg.vendingmachine.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller = ctx.getBean("controller", VendingMachineController.class);

        try {
            controller.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
