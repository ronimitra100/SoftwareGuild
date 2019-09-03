package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.*;
import com.sg.vendingmachine.dto.*;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class VendingMachineServiceLayerTest {

    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);
    }

    /**
     * Test of getAllItems method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetAllItems() throws Exception {
        assertEquals(3, service.getAllItems().size());
    }

    /**
     * Tests of getItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetItemWithValidItemIds() throws Exception {
        Item item1, item2, item3;
        item1 = new Item("001");
        item1.setItemName("Chocolate");
        item1.setItemCost(new BigDecimal("10.00"));
        item1.setItemQuantity(11);

        item2 = new Item("002");
        item2.setItemName("Ice Cream");
        item2.setItemCost(new BigDecimal("20.00"));
        item2.setItemQuantity(12);

        Item item1FromService = service.getItem("001");
        Item item2FromService = service.getItem("002");

        assertEquals(item1, item1FromService);
        assertEquals(item2, item2FromService);

    }

    @Test
    public void testGetItemWithInvalidItemId() throws Exception {
        Item item = service.getItem("007");
        assertNull(item);

    }

    /**
     * Tests of vendItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testVendItemForValidAndAvailable() throws Exception {
        Item item1;
        item1 = new Item("001");
        item1.setItemName("Chocolate");
        item1.setItemCost(new BigDecimal("10.00"));
        item1.setItemQuantity(11);

        Item item1FromDao = service.vendItem("001", new BigDecimal("10.89"));

        assertEquals(item1.getItemQuantity() - 1, item1FromDao.getItemQuantity());
        assertEquals(item1.getItemId(), item1FromDao.getItemId());
        assertEquals(item1.getItemCost(), item1FromDao.getItemCost());
        assertEquals(item1.getItemName(), item1FromDao.getItemName());
    }

    @Test(expected = NoItemInventoryException.class)
    public void testVendItemForInvalidItemId() throws Exception {
        Item vendedItem = service.vendItem("007", new BigDecimal("23.64"));

    }

    @Test(expected = NoItemInventoryException.class)
    public void testVendItemForOutOfStockItem() throws Exception {
        Item vendedItem = service.vendItem("003", new BigDecimal("23.64"));
    }

    @Test(expected = InsufficientFundsException.class)
    public void testVendItemWithInsufficientCustomerDeposit() throws Exception {
        Item vendedItem = service.vendItem("001", new BigDecimal("5.00"));
    }

    @Test(expected = VendingMachineDataValidationException.class)
    public void testVendItemWithEmptyStringAsName() throws Exception {
        Item vendedItem = service.vendItem("004", new BigDecimal("55.00"));
    }

    /**
     * Tests of dispenseItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testDispenseChangeWhenCustomerDepositExceedsItemCostOfAvailableItem() throws Exception {
        Change change = service.dispenseChange(new BigDecimal("12.67"), service.getItem("001"), true);
        assertEquals(10, change.numOfQuarters);
        assertEquals(1, change.numOfDimes);
        assertEquals(1, change.numOfNickels);
        assertEquals(2, change.numOfPennies);
    }

    @Test
    public void testDispenseChangeWhenItemIsOutOfStock() throws Exception {
        Change change = service.dispenseChange(new BigDecimal("12.67"), service.getItem("001"), false);
        assertEquals(50, change.numOfQuarters);
        assertEquals(1, change.numOfDimes);
        assertEquals(1, change.numOfNickels);
        assertEquals(2, change.numOfPennies);
    }

    @Test
    public void testDispenseChangeWhenItemIdIsInvalid() throws Exception {
        Change change = service.dispenseChange(new BigDecimal("12.67"), service.getItem("007"), false);
        assertEquals(50, change.numOfQuarters);
        assertEquals(1, change.numOfDimes);
        assertEquals(1, change.numOfNickels);
        assertEquals(2, change.numOfPennies);
    }

    @Test
    public void testDispenseChangeWhenCustomerDepositIsInsufficient() throws Exception {
        Change change = service.dispenseChange(new BigDecimal("1.42"), service.getItem("007"), false);
        assertEquals(5, change.numOfQuarters);
        assertEquals(1, change.numOfDimes);
        assertEquals(1, change.numOfNickels);
        assertEquals(2, change.numOfPennies);
    }
    
    @Test
    public void testDispenseChangeWhenCustomerDepositEqualsItemCost() throws Exception{
        Change change = service.dispenseChange(new BigDecimal("10.00"), service.getItem("001"), true);
        assertEquals(0, change.numOfQuarters);
        assertEquals(0, change.numOfDimes);
        assertEquals(0, change.numOfNickels);
        assertEquals(0, change.numOfPennies);
    }

}
