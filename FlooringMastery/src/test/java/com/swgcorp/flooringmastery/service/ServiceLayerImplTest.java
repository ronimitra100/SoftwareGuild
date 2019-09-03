package com.swgcorp.flooringmastery.service;

import com.swgcorp.flooringmastery.dto.Order;
import com.swgcorp.flooringmastery.dao.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceLayerImplTest {
    private ServiceLayer service;
    private OrderDao orderDao;

    public ServiceLayerImplTest() {
         ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", ServiceLayer.class);
    }

    
    @Before
    public void setUp() {
        orderDao = new OrderDaoStubImpl();
        
    }

    @Test
    public void testAddOrder() throws Exception {
        Order order1 = new Order("Roni1 Mitra1", "MI", "Carpet", new BigDecimal("100.00"));
        
        
        order1.setOrderNumber(1);
        order1.setTaxRate(new BigDecimal("5.75"));
        order1.setCostPerSqFt(new BigDecimal("2.25"));
        order1.setLaborCostPerSqFt(new BigDecimal("2.10"));
        order1.setMaterialCost(new BigDecimal("225"));
        order1.setLaborCost(new BigDecimal("210"));
        order1.setTax(new BigDecimal("25.01"));
        order1.setOrderTotal(new BigDecimal("460.01"));
        order1.setOrderDate(getTodaysUTCDateInMMddyyyyFormat());
        
        assertEquals(order1,service.addOrder("Roni1 Mitra1", "MI", "Carpet", new BigDecimal("100.00")));
    }
    
  @Test
    public void testDisplayAllOrders() throws Exception {
        List<Order> listOfOrders = service.displayAllOrdersPlacedOnAGivenDate("12312018");
        assertEquals(2, listOfOrders.size());
    }

    @Test
    public void testEditOrder() throws Exception {
        Order orderWithUpdatedAttributes = new Order("RoniMitra", "PA", "Tile", new BigDecimal("10000.00"));
        
        
        orderWithUpdatedAttributes.setOrderNumber(1);
        orderWithUpdatedAttributes.setTaxRate(new BigDecimal("6.75"));
        orderWithUpdatedAttributes.setCostPerSqFt(new BigDecimal("3.50"));
        orderWithUpdatedAttributes.setLaborCostPerSqFt(new BigDecimal("4.15"));
        orderWithUpdatedAttributes.setMaterialCost(new BigDecimal("35000.00"));
        orderWithUpdatedAttributes.setLaborCost(new BigDecimal("41500.00"));
        orderWithUpdatedAttributes.setTax(new BigDecimal("5163.75"));
        orderWithUpdatedAttributes.setOrderTotal(new BigDecimal("81663.75"));
        orderWithUpdatedAttributes.setOrderDate(getTodaysUTCDateInMMddyyyyFormat());
        System.out.println("Actual: " + service.editOrder(1, "RoniMitra", "PA", getTodaysUTCDateInMMddyyyyFormat(), "Tile", new BigDecimal("10000.00")));
        
        assertEquals(orderWithUpdatedAttributes,service.editOrder(1, "RoniMitra", "PA", getTodaysUTCDateInMMddyyyyFormat(), "Tile", new BigDecimal("10000.00")));
    }
    
    @Test
     public void testRemoveOrder() throws Exception {
        
        Order order1 = new Order("Roni1 Mitra1", "MI", "Carpet", new BigDecimal("100.00"));
        
        
        order1.setOrderNumber(1);
        order1.setTaxRate(new BigDecimal("5.75"));
        order1.setCostPerSqFt(new BigDecimal("2.25"));
        order1.setLaborCostPerSqFt(new BigDecimal("2.10"));
        order1.setMaterialCost(new BigDecimal("225"));
        order1.setLaborCost(new BigDecimal("210"));
        order1.setTax(new BigDecimal("25.01"));
        order1.setOrderTotal(new BigDecimal("460.01"));
        order1.setOrderDate(getTodaysUTCDateInMMddyyyyFormat());
        
        assertEquals(order1,service.removeOrder(1,getTodaysUTCDateInMMddyyyyFormat()));
    }
    

  
    public String getTodaysUTCDateInMMddyyyyFormat() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date).toString();
    }

}