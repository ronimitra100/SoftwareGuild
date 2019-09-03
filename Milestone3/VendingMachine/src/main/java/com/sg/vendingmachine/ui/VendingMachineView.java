package com.sg.vendingmachine.ui;

import java.util.List;
import com.sg.vendingmachine.dto.*;
import java.math.BigDecimal;

public class VendingMachineView {

    UserIO io;

    public int printMenuAndGetSelection() {

        io.print("Main Menu");
        io.print("1. Purchase an Item");
        io.print("2. Exit");
        return io.readInt("Please select from the above choices", 1, 2);
    }

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void displayAvailableItemsList(List<Item> itemList) {
        for (Item currentItem : itemList) {
            if (currentItem.getItemQuantity() > 0) {
                io.print(currentItem.getItemId() + ":"
                        + currentItem.getItemName() + ":"
                        + currentItem.getItemCost() + ":"
                        + currentItem.getItemQuantity());
            }
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDiplayAllAvailableItemsBanner() {
        io.print("=== Items Available for Purchase ===");
    }

    public void displayDisplayItemBanner() {
        io.print("=== Chosen Item ===");
    }

    public String getItemIdChoice() {
        return io.readString("Please enter the Item ID of item you wish to purchase.");
    }

    public void displayItem(Item item) {
        if (item != null) {
            io.print(item.getItemId() + ":"
                    + item.getItemName() + ":"
                    + item.getItemCost() + ":"
                    + item.getItemQuantity());
            io.print(" ");
        } else {
            io.print("No such item.");
        }
        io.readString("Please enter hit to continue");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown command");
    }

    public void displayErrorMessage(String errMsg) {
        io.print("=== ERROR ===");
        io.print(errMsg);
    }

    public BigDecimal getCustomerDeposit() {
        return io.readCurrency("Enter money to make a purchase");
    }

    public void displayMakeAPurchaseBanner() {
        io.print("=== Make a Purchase. ===");
    }

    public void displayPurchaseSuccessfulBanner() {
        io.print("=== Purchase was successful. ===");
    }
}
