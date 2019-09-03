package com.swgcorp.flooringmastery.controller;

import com.swgcorp.flooringmastery.dao.*;
import com.swgcorp.flooringmastery.dto.*;
import com.swgcorp.flooringmastery.ui.*;
import com.swgcorp.flooringmastery.service.*;
import java.util.List;
import java.math.BigDecimal;

public class Controller {

    View view;
    private UserIO io = new UserIOConsoleImpl();
    private ServiceLayer service;

    public Controller(ServiceLayer service, View view) {
        this.service = service;
        this.view = view;
    }

    public void run() throws PersistenceException,OrderNumberIsRequiredException, OrderDateIsRequiredException, OrderNotFoundException, CustomerNameIsRequiredException, InvalidStateException, InvalidProductTypeException, InvalidAreaException 
    {
        boolean keepGoing = true;
        int menuSelection = 0;

        
            while (keepGoing) {
                try{
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listOrders();
                        break;
                    case 2:
                        addOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        saveCurrentWork();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
        catch (OrderNumberIsRequiredException| OrderDateIsRequiredException| OrderNotFoundException| CustomerNameIsRequiredException| InvalidStateException| InvalidProductTypeException| InvalidAreaException| PersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }} 
        
       
        
        exitMessage();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    public void addOrder() throws PersistenceException, OrderNumberIsRequiredException, OrderDateIsRequiredException, OrderNotFoundException, CustomerNameIsRequiredException, InvalidStateException, InvalidProductTypeException, InvalidAreaException {
        view.displayAddOrderBanner();

        String customerName = view.getCustomerNameForNewOrder();
        String state = view.getStateForNewOrder();
        String productType = view.getProductTypeForNewOrder();
        BigDecimal area = view.getAreaForNewOrder();

        service.addOrder(customerName, state, productType, area);
        view.displayOrderSuccessfullyAddedBanner();
        
        if (view.isCurrentWorkToBeSaved()) {
            service.saveCurrentWork();
        }
    }

    private void listOrders() throws PersistenceException {
        view.displayListOfAllOrdersBanner();
        String orderDate = view.getOrderDateOfOrderToBeOperatedOn();
        List<Order> listOfOrders = service.displayAllOrdersPlacedOnAGivenDate(orderDate);
        view.displayOrdersPlacedOnAGivenDate(listOfOrders);
    }

    private void viewOrder() throws PersistenceException, OrderNumberIsRequiredException, OrderDateIsRequiredException, OrderNotFoundException {
        view.displayDisplayOrderBanner();

        Integer orderNumber = view.getOrderNumberOfOrderToBeOperatedOn();
        String orderDate = view.getOrderDateOfOrderToBeOperatedOn();
        Order order = service.getOrderDetails(orderNumber, orderDate);

        view.displayInfoAboutAGivenOrder(order);
    }

    public void removeOrder() throws PersistenceException, OrderNumberIsRequiredException, OrderDateIsRequiredException, OrderNotFoundException {
        view.displayRemoveOrderBanner();

        Integer orderNumber = view.getOrderNumberOfOrderToBeOperatedOn();
        String orderDate = view.getOrderDateOfOrderToBeOperatedOn();
        Order order = service.removeOrder(orderNumber, orderDate);

        view.displayOrderSuccessfullyRemovedBanner();

        if (view.isCurrentWorkToBeSaved()) {
            service.saveCurrentWork();
        }
    }

    public void editOrder() throws PersistenceException, OrderNumberIsRequiredException, OrderDateIsRequiredException, OrderNotFoundException, CustomerNameIsRequiredException, InvalidStateException, InvalidProductTypeException, InvalidAreaException {
        view.displayUpdateOrderBanner();

        Integer orderNumber = view.getOrderNumberOfOrderToBeOperatedOn();
        String orderDate = view.getOrderDateOfOrderToBeOperatedOn();
        Order order = service.getOrderDetails(orderNumber, orderDate);

        String customerName = view.updateCustomerNameForExistingOrder(order);
        String state = view.updateStateForExistingOrder(order);
        String productType = view.updateProductTypeForExistingOrder(order);
        BigDecimal area = view.updateAreaForExistingOrder(order);

        service.editOrder(orderNumber, customerName, state, orderDate, productType, area);

        view.displayOrderSuccessfullyUpdatedBanner();

        if (view.isCurrentWorkToBeSaved()) {
            service.saveCurrentWork();
        }
    }

    public void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    public void exitMessage() {
        view.displayExitMessage();
    }

    public void saveCurrentWork() throws PersistenceException {
        if (view.isCurrentWorkToBeSaved()) {
            service.saveCurrentWork();
        }
    }

    /*public void getAllOrderDates() throws PersistenceException {
        for (String orderDate : service.getAllOrderDates()) {
            System.out.println(orderDate);
        }
    }*/

}
