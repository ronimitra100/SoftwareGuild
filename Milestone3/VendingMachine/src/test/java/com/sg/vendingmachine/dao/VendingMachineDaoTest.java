package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendingMachineDaoTest {

    VendingMachineDao dao = new VendingMachineDaoStubImpl();

    @Test
    public void testGetAllItems() throws Exception {
        assertEquals(3, dao.getAllItems().size());
    }

    @Test
    public void testGetItemForValidItemIds() throws Exception {
        Item item1, item2, item3;
        item1 = new Item("001");
        item1.setItemName("Chocolate");
        item1.setItemCost(new BigDecimal("10.00"));
        item1.setItemQuantity(11);

        item2 = new Item("002");
        item2.setItemName("Ice Cream");
        item2.setItemCost(new BigDecimal("20.00"));
        item2.setItemQuantity(12);

        item3 = new Item("003");
        item3.setItemName("Milkshake");
        item3.setItemCost(new BigDecimal("30.00"));
        item3.setItemQuantity(0);

        Item item1FromDao = dao.getItem("001");
        Item item2FromDao = dao.getItem("002");
        Item item3FromDao = dao.getItem("003");

        assertEquals(item1, item1FromDao);
        assertEquals(item2, item2FromDao);
        assertEquals(item3, item3FromDao);

    }

    @Test
    public void testVendItemForValidItemId() throws Exception {
        Item item1;
        item1 = new Item("001");
        item1.setItemName("Chocolate");
        item1.setItemCost(new BigDecimal("10.00"));
        item1.setItemQuantity(11);

        Item item1FromDao = dao.vendItem("001");

        assertEquals(item1.getItemQuantity() - 1, item1FromDao.getItemQuantity());
        assertEquals(item1.getItemId(), item1FromDao.getItemId());
        assertEquals(item1.getItemCost(), item1FromDao.getItemCost());
        assertEquals(item1.getItemName(), item1FromDao.getItemName());
    }

    @Test
    public void testGetItemForInvalidItemId() throws Exception {

        assertNull(dao.getItem("005"));

    }

    @Test
    public void testVendItemForInvalidItemId() throws Exception {

        assertNull(dao.vendItem("005"));

    }

}
