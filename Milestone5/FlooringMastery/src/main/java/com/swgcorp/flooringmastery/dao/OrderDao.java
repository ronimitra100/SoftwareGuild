package com.swgcorp.flooringmastery.dao;

import com.swgcorp.flooringmastery.dto.*;
import java.util.List;
import java.math.BigDecimal;

public interface OrderDao {

    public Order createOrder(Order order) throws PersistenceException;

    public Order updateOrder(Integer orderNumber, String orderDate, Order order) throws PersistenceException;

    public Order getOrder(Integer orderNumber, String orderDate) throws PersistenceException;

    public Order deleteOrder(Integer orderNumber, String orderDate) throws PersistenceException;

    public List<Order> getAllOrders(String orderDate) throws PersistenceException;

    public List<String> getAllOrderDates() throws PersistenceException;

    public void saveCurrentWork() throws PersistenceException;

    public void loadOrderData(String orderDate) throws PersistenceException;

    public Integer getOrderNumberForNewOrder(String orderDate) throws PersistenceException;

}
