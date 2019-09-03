package com.swgcorp.flooringmastery.dao;

import com.swgcorp.flooringmastery.dto.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import java.util.NoSuchElementException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

public class OrderDaoStubImpl implements OrderDao {
     private Map<String, Map<Integer, Order>> orders = new HashMap<>();
    Order order1, order2, order3;
    ArrayList<Order> listOfOrders = new ArrayList<Order>();
    
    
    public OrderDaoStubImpl(){
        
        order1 = new Order("Roni1 Mitra1", "MI", "Carpet", new BigDecimal("100.00"));
        order2 = new Order("Roni2 Mitra2", "IN", "Laminate", new BigDecimal("1000.00"));
        order3 = new Order("Roni3 Mitra3", "OH", "Wood", new BigDecimal("200.00"));
        
        order1.setOrderNumber(1);
        order1.setTaxRate(new BigDecimal("5.75"));
        order1.setCostPerSqFt(new BigDecimal("2.25"));
        order1.setLaborCostPerSqFt(new BigDecimal("2.10"));
        order1.setMaterialCost(new BigDecimal("225"));
        order1.setLaborCost(new BigDecimal("210"));
        order1.setTax(new BigDecimal("25.01"));
        order1.setOrderTotal(new BigDecimal("460.01"));
        order1.setOrderDate(getTodaysUTCDateInMMddyyyyFormat());
        
        order2.setOrderNumber(2);
        order2.setTaxRate(new BigDecimal("6.00"));
        order2.setCostPerSqFt(new BigDecimal("1.75"));
        order2.setLaborCostPerSqFt(new BigDecimal("2.10"));
        order2.setMaterialCost(new BigDecimal("175"));
        order2.setLaborCost(new BigDecimal("210"));
        order2.setTax(new BigDecimal("23.10"));
        order2.setOrderTotal(new BigDecimal("408.10"));
        order2.setOrderDate("12312018");
        
        order3.setOrderNumber(3);
        order3.setTaxRate(new BigDecimal("6.25"));
        order3.setCostPerSqFt(new BigDecimal("5.15"));
        order3.setLaborCostPerSqFt(new BigDecimal("4.75"));
        order3.setMaterialCost(new BigDecimal("1030"));
        order3.setLaborCost(new BigDecimal("950"));
        order3.setTax(new BigDecimal("12.50"));
        order3.setOrderTotal(new BigDecimal("1992.50"));
        order3.setOrderDate("12312018");
        
        listOfOrders.add(order1);
        listOfOrders.add(order2);
        listOfOrders.add(order3);
        
        
    }
    

    @Override
    public Order createOrder(Order order) throws PersistenceException {
        if (order.getOrderNumber()==1){
            return order1;
        }
        else if (order.getOrderNumber()==2){
            return order2;
        }
        else if (order.getOrderNumber()==3){
            return order3;
        }
        else{
            return null;
        }
    }

    @Override
    public Order updateOrder(Integer orderNumber, String orderDate, Order order) throws PersistenceException {
        Integer ordNum = order.getOrderNumber();
        if (ordNum==1||ordNum==2||ordNum==3){
            return order;
        }
        else{
            return null;
        }
    }

    @Override
    public Order getOrder(Integer orderNumber, String orderDate) throws PersistenceException {
        
        if (orderNumber==1){
            return order1;
        }
        else if (orderNumber==2){
            return order2;
        }
        else if (orderNumber==3){
            return order3;
        }
        else{
            return null;
        }
    }

    @Override
    public Order deleteOrder(Integer orderNumber, String orderDate) throws PersistenceException {
        if (orderNumber==1){
            return order1;
        }
        else if (orderNumber==2){
            return order2;
        }
        else if (orderNumber==3){
            return order3;
        }
        else{
            return null;
        }
    }

    @Override
    public List<Order> getAllOrders(String orderDate) throws PersistenceException {
       List<Order> allOrders = new ArrayList<Order>();
        if (orderDate.equals(getTodaysUTCDateInMMddyyyyFormat())){
            allOrders.add(order1);
        }
        else if (orderDate.equals("12312018")){
            allOrders.add(order2);
            allOrders.add(order3);
        }
        return allOrders;
       
    }

    @Override
    public void saveCurrentWork() throws PersistenceException {
       //Do nothing
    }

    @Override
    public void loadOrderData(String orderDate) throws PersistenceException {
       //Do nothing
    }

    @Override
    public Integer getOrderNumberForNewOrder(String orderDate) throws PersistenceException {
        return 1;
    }

    @Override
    public List<String> getAllOrderDates() throws PersistenceException {
        List<String> allOrderDates = new ArrayList<String>();
        allOrderDates.add(getTodaysUTCDateInMMddyyyyFormat());
        allOrderDates.add("12312018");
        return allOrderDates;
    }
    
    public String getTodaysUTCDateInMMddyyyyFormat() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date).toString();
    }
}

