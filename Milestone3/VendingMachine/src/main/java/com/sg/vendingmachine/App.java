package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.*;
import com.sg.vendingmachine.ui.*;
import com.sg.vendingmachine.dao.*;
import com.sg.vendingmachine.dto.*;
import com.sg.vendingmachine.service.*;

public class App {

    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIo);
        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
        VendingMachineController controller = new VendingMachineController(myService, myView);

        try {
            controller.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
