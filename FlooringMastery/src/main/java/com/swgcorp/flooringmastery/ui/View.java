package com.swgcorp.flooringmastery.ui;

import com.swgcorp.flooringmastery.dto.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class View {

    UserIO io;

    public View(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print(" *  <<Flooring Program>>");
        io.print("* 1. Display Orders");
        io.print("* 2. Add an Order");
        io.print("* 3. Edit an Order");
        io.print("* 4. Remove an Order");
        io.print("* 5. Save Current Work");
        io.print("* 6. Quit");
        io.print("*");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public String getCustomerNameForNewOrder() {
        return io.readString("Please enter customer name.");
    }

    public String getStateForNewOrder() {
        return io.readString("Please enter abbreviation of the state.");
    }

    public BigDecimal getAreaForNewOrder() {
        return io.readPositiveNumberWithTwoDecimalPlaces("Please enter surface area in square footage.");
    }

    public String getProductTypeForNewOrder() {
        return io.readString("Please enter product type.");
    }

    public String updateCustomerNameForExistingOrder(Order order) {
        return io.readString("Enter customer name (" + order.getCustomerName() + "):");
    }

    public String updateStateForExistingOrder(Order order) {
        return io.readString("Enter abbreviation of state (" + order.getState() + "):");
    }

    public BigDecimal updateAreaForExistingOrder(Order order) {
        return io.readPositiveNumberWithTwoDecimalPlaces("Enter surface area in square footage (" + order.getArea() + "):");
    }

    public String updateProductTypeForExistingOrder(Order order) {
        return io.readString("Enter product type (" + order.getProductType() + "):");
    }

    public Integer getOrderNumberOfOrderToBeOperatedOn() {
        return io.readInt("Please enter order number of order you want to view/update/remove");
    }

    public String getOrderDateOfOrderToBeOperatedOn() {
        return io.readString("Please enter order date of order you want to view/update/remove");
    }

    public boolean isCurrentWorkToBeSaved() {
        String answerToSaveChangesQuestion = io.readString("Please review your changes and enter Y/Yes to save them.");
        return (answerToSaveChangesQuestion.toLowerCase().equals("y") || answerToSaveChangesQuestion.toLowerCase().equals("yes"));
    }

    public void displayInfoAboutAGivenOrder(Order order) {
        if (order != null) {
            io.print("Order Number: " + order.getOrderNumber());
            io.print("Customer Name: " + order.getCustomerName());
            io.print("State: " + order.getState());
            io.print("Tax Rate: " + order.getTaxRate());
            io.print("Product Type: " + order.getProductType());
            io.print("Area: " + order.getArea());
            io.print("Cost per Square Feet: " + order.getCostPerSqFt());
            io.print("Labor Cost per Square Feet: " + order.getLaborCostPerSqFt());
            io.print("Material Cost: " + order.getMaterialCost());
            io.print("Labor Cost: " + order.getLaborCost());
            io.print("Tax: " + order.getTax());
            io.print("Order Total: " + order.getOrderTotal());
            io.print("Order Date: " + order.getOrderDate());
        }
    }

    public void displayOrdersPlacedOnAGivenDate(List<Order> listOfOrders) {

        for (Order order : listOfOrders) {
            io.print("-------------------------------");
            displayInfoAboutAGivenOrder(order);
        }
    }

    public void displayAddOrderBanner() {
        io.print("=== Place an Order. ===");
    }

    public void displayOrderSuccessfullyAddedBanner() {
        io.print("=== Order has been successfully added. ===");
    }

    public void displayUpdateOrderBanner() {
        io.print("=== Update Order Information. ===");
    }

    public void displayOrderSuccessfullyUpdatedBanner() {
        io.print("=== Order has been successfully updated.===");
    }

    public void displayDisplayOrderBanner() {
        io.print("=== Information about chosen Order. ===");
    }

    public void displayOrdersSuccessfullyFetchedBanner() {
        io.print("=== Order information has been successfully fetched.===");
    }

    public void displayRemoveOrderBanner() {
        io.print("=== Remove Order. ===");
    }

    public void displayOrderSuccessfullyRemovedBanner() {
        io.print("=== Order has been successfully removed.===");
    }

    public void displayListOfAllOrdersBanner() {
        io.print("=== List of all Orders placed on chosen Order Date. ===");
    }

    public void displayExitMessage() {
        io.print("Goodbye!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command");
    }

    public void displayErrorMessage(String errMsg) {
        io.print("=== Error ===");
        io.print(errMsg);
    }

}
