package com.swgcorp.flooringmastery.service;

import com.swgcorp.flooringmastery.dao.*;
import com.swgcorp.flooringmastery.dto.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

public class ServiceLayerImpl implements ServiceLayer {

    OrderDao orderDao = new OrderDaoProdImpl();
    ProductDao productDao = new ProductDaoImpl();
    StateTaxDao stateTaxDao = new StateTaxDaoImpl();
    AuditDao auditDao = new AuditDaoImpl();

    public ServiceLayerImpl(OrderDao orderDao, ProductDao productDao, StateTaxDao stateTaxDao, AuditDao auditDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.stateTaxDao = stateTaxDao;
        this.auditDao = auditDao;

    }

    @Override
    public List<Order> displayAllOrdersPlacedOnAGivenDate(String orderDate) throws PersistenceException {
        return orderDao.getAllOrders(orderDate);
    }

    @Override
    public Order addOrder(String customerName, String stateAbbr, String productType, BigDecimal area) throws CustomerNameIsRequiredException, InvalidStateException, InvalidProductTypeException, InvalidAreaException, PersistenceException {
        this.isValidCustomerName(customerName);
        this.isValidStateAbbr(stateAbbr);
        this.isvalidProductType(productType);

        Order order = new Order(customerName, stateAbbr, productType, area);
        order.setTaxRate(getStateTaxRate(stateAbbr));
        order.setCostPerSqFt(getCostPerSqFoot(productType));
        order.setLaborCostPerSqFt(getLaborCostPerSqFoot(productType));
        order.setMaterialCost(getMaterialCost(productType, area));
        order.setLaborCost(getLaborCost(productType, area));
        order.setOrderDate(getOrderDateForNewOrder());
        order.setOrderNumber(getOrderNumberForNewOrder(getOrderDateForNewOrder()));
        order.setTax(getTaxAmount(productType, area, stateAbbr));
        order.setOrderTotal(getOrderTotal(productType, area, stateAbbr));

        return orderDao.createOrder(order);
    }

    @Override
    public Order editOrder(Integer orderNumber, String customerName, String stateAbbr, String orderDate, String productType, BigDecimal area) throws OrderNotFoundException, CustomerNameIsRequiredException, InvalidStateException, InvalidProductTypeException, InvalidAreaException, PersistenceException, OrderNumberIsRequiredException, OrderNumberIsRequiredException, OrderDateIsRequiredException {
        String updatedCustomerName;
        String updatedStateAbbr;
        String updatedProductType;
        BigDecimal updatedArea;

        this.isInputDataToLookupOrderValid(orderNumber, orderDate);
        Order order = this.getOrderDetails(orderNumber, orderDate);

        if (customerName == null || customerName.trim().length() == 0) {
            updatedCustomerName = order.getCustomerName();
        } else {
            updatedCustomerName = customerName;
        }

        if (stateAbbr == null || stateAbbr.trim().length() == 0 || !(isValidStateAbbr(stateAbbr))) {
            updatedStateAbbr = order.getState();
        } else {
            updatedStateAbbr = stateAbbr;
        }

        if (productType == null || productType.trim().length() == 0 || !(isvalidProductType(productType))) {
            updatedProductType = order.getProductType();
        } else {
            updatedProductType = productType;
        }

        if (area == null || !this.isValidArea(area)) {
            updatedArea = order.getArea();
        } else {
            updatedArea = area;
        }

        Order updatedOrder = new Order(updatedCustomerName, updatedStateAbbr, updatedProductType, updatedArea);

        updatedOrder.setOrderNumber(orderNumber);
        updatedOrder.setOrderDate(orderDate);
        updatedOrder.setTaxRate(getStateTaxRate(updatedStateAbbr));
        updatedOrder.setCostPerSqFt(getCostPerSqFoot(updatedProductType));
        updatedOrder.setLaborCostPerSqFt(getLaborCostPerSqFoot(updatedProductType));
        updatedOrder.setMaterialCost(getMaterialCost(updatedProductType, updatedArea));
        updatedOrder.setLaborCost(getLaborCost(updatedProductType, updatedArea));
        updatedOrder.setTax(getTaxAmount(updatedProductType, updatedArea, updatedStateAbbr));
        updatedOrder.setOrderTotal(getOrderTotal(updatedProductType, updatedArea, updatedStateAbbr));
        return orderDao.updateOrder(orderNumber, orderDate, updatedOrder);
    }

    @Override
    public Order removeOrder(Integer orderNumber, String orderDate) throws OrderNumberIsRequiredException, OrderDateIsRequiredException, OrderNotFoundException, PersistenceException {
        validateInputDataToLookupOrder(orderNumber, orderDate);
        return orderDao.deleteOrder(orderNumber, orderDate);
    }

    @Override
    public void saveCurrentWork() throws PersistenceException {
        orderDao.saveCurrentWork();
    }

    @Override
    public Order getOrderDetails(Integer orderNumber, String orderDate) throws OrderNumberIsRequiredException, OrderDateIsRequiredException, OrderNotFoundException, PersistenceException {
        validateInputDataToLookupOrder(orderNumber, orderDate);
        return orderDao.getOrder(orderNumber, orderDate);
    }

    public String getOrderDateForNewOrder() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date).toString();
    }

    public Integer getOrderNumberForNewOrder(String orderDate) throws PersistenceException {
        return orderDao.getOrderNumberForNewOrder(orderDate);
    }

    public BigDecimal getCostPerSqFoot(String productType) throws PersistenceException {
        return productDao.getCostPerSquareFoot(productType);
    }

    public BigDecimal getMaterialCost(String productType, BigDecimal area) throws PersistenceException {
        return productDao.getCostPerSquareFoot(productType).multiply(area).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getLaborCostPerSqFoot(String productType) throws PersistenceException {
        return productDao.getLaborCostPerSquareFoot(productType);
    }

    public BigDecimal getLaborCost(String productType, BigDecimal area) throws PersistenceException {
        return productDao.getLaborCostPerSquareFoot(productType).multiply(area).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getStateTaxRate(String stateAbbr) throws PersistenceException {
        return stateTaxDao.getTaxForGivenState(stateAbbr);
    }

    public BigDecimal getTaxAmount(String productType, BigDecimal area, String stateAbbr) throws PersistenceException {
        BigDecimal materialCost = this.getMaterialCost(productType, area);
        BigDecimal laborCost = this.getLaborCost(productType, area);
        BigDecimal subTotal = materialCost.add(laborCost);
        BigDecimal taxRate = this.getStateTaxRate(stateAbbr);
        return subTotal.multiply(taxRate).divide(new BigDecimal(100.00)).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getOrderTotal(String productType, BigDecimal area, String stateAbbr) throws PersistenceException {
        BigDecimal materialCost = this.getMaterialCost(productType, area);
        BigDecimal laborCost = this.getLaborCost(productType, area);
        BigDecimal taxAmount = this.getTaxAmount(productType, area, stateAbbr);
        BigDecimal orderTotal = materialCost.add(laborCost).add(taxAmount).setScale(2, RoundingMode.HALF_UP);
        return orderTotal;
    }

    public void validInputDataForGeneratingNewOrUpdatedOrders(String customerName, String stateAbbr, String productType, BigDecimal area)
            throws CustomerNameIsRequiredException, InvalidStateException, InvalidProductTypeException, PersistenceException {
        if (customerName == null || customerName.trim().length() == 0) {
            throw new CustomerNameIsRequiredException("ERROR: Customer Name is required.");
        }

        if (stateAbbr == null || !stateTaxDao.isStateAbbrValid(stateAbbr)) {
            throw new InvalidStateException("ERROR: State Abbreviation entered is invalid.");
        }

        if (productType == null || !productDao.isProductTypeValid(productType)) {
            throw new InvalidProductTypeException("ERROR: State Abbreviation entered is invalid.");
        }
    }

    public boolean isValidCustomerName(String customerName)
            throws CustomerNameIsRequiredException, PersistenceException {
        if (customerName == null || customerName.trim().length() == 0) {
            throw new CustomerNameIsRequiredException("ERROR: Customer Name is required.");
        }

        return true;
    }

    public boolean isValidStateAbbr(String stateAbbr) throws InvalidStateException, PersistenceException {
        if (stateAbbr == null || !stateTaxDao.isStateAbbrValid(stateAbbr)) {
            throw new InvalidStateException("ERROR: State Abbreviation entered is invalid.");
        }

        return true;
    }

    public boolean isvalidProductType(String productType) throws InvalidProductTypeException, PersistenceException {
        if (productType == null || !productDao.isProductTypeValid(productType)) {
            throw new InvalidProductTypeException("ERROR: The Product type entered is invalid.");
        }

        return true;
    }

    public boolean isValidArea(BigDecimal area) throws InvalidAreaException {
        if (!(area.toString().matches("[0-9]+([.][0-9]{1,2})?"))|| area.toString().equals("0.00")) {
            throw new InvalidAreaException("ERROR: The enter area is invalid. It should be a positive integer with two places of decimals.");
        }
        return true;
    }

    public boolean isInputDataToLookupOrderValid(Integer orderNumber, String orderDate) throws OrderNotFoundException, PersistenceException {
        boolean isInputDataToLookupOrderValid = false;

        if (!(orderDao.getAllOrderDates().contains(orderDate) && orderDao.getAllOrders(orderDate).stream().anyMatch(o -> o.getOrderNumber() == orderNumber))) {
            throw new OrderNotFoundException("ERROR: No order matching the order date and order number provided was found.");
        }

        isInputDataToLookupOrderValid = true;
        return isInputDataToLookupOrderValid;
    }

    public void validateInputDataToLookupOrder(Integer orderNumber, String orderDate) throws OrderNotFoundException, PersistenceException {

        boolean isOrderDateValid = orderDao.getAllOrderDates().contains(orderDate);
        System.out.println("isOrderDateValid: " + isOrderDateValid);
        boolean isOrderNumberValidForGivenOrderDate = (orderDao.getAllOrders(orderDate).stream().anyMatch(o -> o.getOrderNumber() == orderNumber));
        System.out.println("isOrderNumberValidForGivenOrderDate: " + isOrderNumberValidForGivenOrderDate);
        if (!isOrderDateValid) {
            throw new OrderNotFoundException("ERROR: No orders matching the specificief order date were found.");
        }
        if (!(isOrderDateValid && isOrderNumberValidForGivenOrderDate)) {
            throw new OrderNotFoundException("ERROR: No order matching the order date and order number provided was found.");
        }
    }

    @Override
    public List<String> getAllOrderDates() throws PersistenceException {
        return orderDao.getAllOrderDates();
    }
}
