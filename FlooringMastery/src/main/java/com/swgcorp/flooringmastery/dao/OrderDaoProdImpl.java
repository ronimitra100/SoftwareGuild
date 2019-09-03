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
import java.util.Arrays;

public class OrderDaoProdImpl implements OrderDao {

    private Map<String, Map<Integer, Order>> orders = new HashMap<>();
    public String fileContainingOrders = "";
    public static final String DELIMITER = ",";
    public static final String LAST_CREATED_TEMP_ORDER = "OrderNumberOfLastCreatedTempOrder.txt";
    public static final String ORDERS_FOLDER = "Orders";

    @Override
    public Order createOrder(Order order) throws PersistenceException {
        HashMap<Integer, Order> mapOfOrdersPlacedOnOrderDate = new HashMap<Integer, Order>();

        if (this.getAllOrderDates().contains(order.getOrderDate())) {
            List<Order> listOfOrdersPlacedOnOrderDate = this.getAllOrders(order.getOrderDate());
            for (Order o : listOfOrdersPlacedOnOrderDate) {
                mapOfOrdersPlacedOnOrderDate.put(o.getOrderNumber(), o);
            }
        }

        mapOfOrdersPlacedOnOrderDate.put(order.getOrderNumber(), order);
        orders.put(order.getOrderDate(), mapOfOrdersPlacedOnOrderDate);

        return order;
    }

    @Override
    public Order updateOrder(Integer orderNumber, String orderDate, Order order) throws PersistenceException {
        Map<Integer, Order> mapOfOrdersPlacedOnOrderDate = new HashMap<>();
        List<Order> ordersFromOrderDate = this.getAllOrders(orderDate);

        for (Order o : ordersFromOrderDate) {
            mapOfOrdersPlacedOnOrderDate.put(o.getOrderNumber(), o);
        }

        mapOfOrdersPlacedOnOrderDate.put(orderNumber, order);
        orders.put(orderDate, mapOfOrdersPlacedOnOrderDate);

        return orders.get(orderDate).get(orderNumber);
    }

    @Override
    public Order getOrder(Integer orderNumber, String orderDate) throws PersistenceException {
        this.loadOrderData(orderDate);
        return orders.get(orderDate).get(orderNumber);
    }

    @Override
    public Order deleteOrder(Integer orderNumber, String orderDate) throws PersistenceException {
        this.loadOrderData(orderDate);
        return orders.get(orderDate).remove(orderNumber);
    }

    @Override
    public List<Order> getAllOrders(String orderDate) throws PersistenceException {
        this.loadOrderData(orderDate);
        return new ArrayList<Order>(orders.get(orderDate).values());
    }

    @Override
    public void saveCurrentWork() throws PersistenceException {
        Set<String> orderDates = orders.keySet();

        for (String orderDate : orderDates) {
            fileContainingOrders = ORDERS_FOLDER + "\\" + "Orders_" + orderDate + ".txt";
            PrintWriter out;

            try {
                out = new PrintWriter(new FileWriter(fileContainingOrders), true);
            } catch (IOException e) {
                throw new PersistenceException("Could not save file", e);
            }

            Map<Integer, Order> mapOfOrdersPlacedOnGivenDate = orders.get(orderDate);
            for (Order currentOrder : mapOfOrdersPlacedOnGivenDate.values()) {
                out.println(currentOrder.getOrderNumber() + DELIMITER
                        + currentOrder.getCustomerName() + DELIMITER
                        + currentOrder.getState() + DELIMITER
                        + currentOrder.getTaxRate() + DELIMITER
                        + currentOrder.getProductType() + DELIMITER
                        + currentOrder.getArea() + DELIMITER
                        + currentOrder.getCostPerSqFt() + DELIMITER
                        + currentOrder.getLaborCostPerSqFt() + DELIMITER
                        + currentOrder.getMaterialCost() + DELIMITER
                        + currentOrder.getLaborCost() + DELIMITER
                        + currentOrder.getTax() + DELIMITER
                        + currentOrder.getOrderTotal()
                );                
            }
            out.flush();
            out.close();
        }
    }

    @Override
    public void loadOrderData(String orderDate) throws PersistenceException {
        if (!getAllOrderDates().contains(orderDate)){
            throw new PersistenceException("ERROR: The order date provided is not valid or there is currently some issue retrieving order data from that date") ;
        }
        fileContainingOrders = ORDERS_FOLDER + "\\" + "Orders_" + orderDate + ".txt";
        
        Map<Integer, Order> ordersFromGivenDate = new HashMap<>();
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(fileContainingOrders)));

        } catch (FileNotFoundException e) {
            throw new PersistenceException("-_- Could not load order data into memory for orders placed on " + orderDate, e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Order currentOrder = new Order(currentTokens[1], currentTokens[2], currentTokens[4], new BigDecimal(currentTokens[5]));

            currentOrder.setOrderNumber(Integer.parseInt(currentTokens[0]));
            currentOrder.setTaxRate(new BigDecimal(currentTokens[3]));
            currentOrder.setCostPerSqFt(new BigDecimal(currentTokens[6]));
            currentOrder.setLaborCostPerSqFt(new BigDecimal(currentTokens[7]));
            currentOrder.setMaterialCost(new BigDecimal(currentTokens[8]));
            currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));
            currentOrder.setTax(new BigDecimal(currentTokens[10]));
            currentOrder.setOrderTotal(new BigDecimal(currentTokens[11]));
            currentOrder.setOrderDate(orderDate);

            ordersFromGivenDate.put(currentOrder.getOrderNumber(), currentOrder);
        }

        orders.put(orderDate, ordersFromGivenDate);
        scanner.close();

    }

    @Override
    public Integer getOrderNumberForNewOrder(String orderDate) throws PersistenceException {
        int maxOrderNumber =0;

        if (this.getAllOrderDates().contains(orderDate)) {
            for (Order o : this.getAllOrders(orderDate)) {
                if (o.getOrderNumber() > maxOrderNumber) {
                    maxOrderNumber = o.getOrderNumber();
                }
            }
        }
        return maxOrderNumber + 1;
    }

    @Override
    public List<String> getAllOrderDates() throws PersistenceException {
        File folder = new File(System.getProperty("user.dir") + "\\" + ORDERS_FOLDER);
        File[] listOfFiles = folder.listFiles();

        List<String> listOfOrderDateFiles = new ArrayList<>();

        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().startsWith("Orders_") && file.getName().endsWith(".txt")) {
                String orderDateLinkedToFile = StringUtils.removeEnd(StringUtils.removeStart(file.getName(), "Orders_"), ".txt");
                listOfOrderDateFiles.add(orderDateLinkedToFile);
            }
        }
        return listOfOrderDateFiles;
    }
}
