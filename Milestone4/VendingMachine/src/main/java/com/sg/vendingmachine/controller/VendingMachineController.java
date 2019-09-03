package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.*;
import com.sg.vendingmachine.dto.*;
import com.sg.vendingmachine.service.*;
import com.sg.vendingmachine.ui.*;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class VendingMachineController {

    VendingMachineView view;
    private UserIO io = new UserIOConsoleImpl();
    private VendingMachineServiceLayer service;

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void run() throws Exception {
        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            while (keepGoing) {
                listAvailableItems();
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        vendItem();
                        break;
                    case 2:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
        } catch (VendingMachineDataValidationException e) {
            view.displayErrorMessage(e.getMessage());
        } catch (NoItemInventoryException e) {
            view.displayErrorMessage(e.getMessage());
        } catch (InsufficientFundsException e) {
            view.displayErrorMessage(e.getMessage());
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        exitMessage();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void listAvailableItems() throws VendingMachinePersistenceException {
        view.displayDiplayAllAvailableItemsBanner();
        List<Item> itemList = service.getAllItems();
        view.displayAvailableItemsList(itemList);
    }

    private void vendItem() throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException, VendingMachineDataValidationException {
        view.displayMakeAPurchaseBanner();
        boolean hasErrors = false;

        BigDecimal customerDeposit = view.getCustomerDeposit();
        String chosenItemId = view.getItemIdChoice();
        Item chosenItem = service.getItem(chosenItemId);
        try {
            Item item = service.vendItem(chosenItemId, customerDeposit);
            view.displayPurchaseSuccessfulBanner();
            service.dispenseChange(customerDeposit, chosenItem, true);
            hasErrors = false;
        } catch (VendingMachineDataValidationException e) {
            hasErrors = true;
            view.displayErrorMessage(e.getMessage());
            service.dispenseChange(customerDeposit, null, true);
        } catch (NoItemInventoryException e) {
            hasErrors = true;
            view.displayErrorMessage(e.getMessage());
            service.dispenseChange(customerDeposit, null, false);
        } catch (InsufficientFundsException e) {
            hasErrors = true;
            view.displayErrorMessage(e.getMessage());
            service.dispenseChange(customerDeposit, chosenItem, true);
        } catch (VendingMachinePersistenceException e) {
            hasErrors = true;
            view.displayErrorMessage(e.getMessage());
            service.dispenseChange(customerDeposit, chosenItem, false);
        }

    }

    public void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    public void exitMessage() {
        view.displayExitBanner();
    }

}
