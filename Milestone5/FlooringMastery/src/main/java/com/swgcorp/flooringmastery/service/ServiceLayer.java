package com.swgcorp.flooringmastery.service;

import com.swgcorp.flooringmastery.dto.*;
import com.swgcorp.flooringmastery.dao.*;
import java.math.BigDecimal;
import java.util.List;

public interface ServiceLayer {

    public List<Order> displayAllOrdersPlacedOnAGivenDate(String date) throws PersistenceException;

    public Order addOrder(String customerName, String state, String ProductType, BigDecimal area) throws CustomerNameIsRequiredException, InvalidStateException, InvalidProductTypeException, InvalidAreaException, PersistenceException;

    public Order editOrder(Integer orderNumber, String customerName, String state, String orderDate, String ProductType, BigDecimal area) throws OrderNumberIsRequiredException,OrderNotFoundException, CustomerNameIsRequiredException, InvalidStateException, InvalidProductTypeException, InvalidAreaException, PersistenceException,OrderDateIsRequiredException;

    public Order removeOrder(Integer orderNumber, String orderDate) throws OrderNumberIsRequiredException, OrderDateIsRequiredException, OrderNotFoundException, PersistenceException;

    public void saveCurrentWork() throws PersistenceException;

    public Order getOrderDetails(Integer orderNumber, String orderDate) throws OrderNumberIsRequiredException, OrderDateIsRequiredException, OrderNotFoundException, PersistenceException;
    
    public List<String> getAllOrderDates() throws PersistenceException;
}
