package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

public class VendingMachineDaoStubImpl implements VendingMachineDao {

    Item item1, item2, item3;
    List<Item> itemList = new ArrayList<Item>();

    public VendingMachineDaoStubImpl() {
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

        item3 = new Item("004");
        item3.setItemName("");
        item3.setItemCost(new BigDecimal("30.00"));
        item3.setItemQuantity(10);

        itemList.addAll(Arrays.asList(item1, item2, item3));

    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return itemList;
    }

    @Override
    public Item getItem(String itemId) throws VendingMachinePersistenceException {

        if (itemId == item1.getItemId()) {
            return item1;
        } else if (itemId == item2.getItemId()) {
            return item2;
        } else if (itemId == item3.getItemId()) {
            return item3;
        } else {
            return null;
        }
    }

    @Override
    public Item vendItem(String itemId) throws VendingMachinePersistenceException {
        item1.setItemQuantity(item1.getItemQuantity() - 1);
        item2.setItemQuantity(item2.getItemQuantity() - 1);
        item3.setItemQuantity(item3.getItemQuantity() - 1);

        if (itemId == item1.getItemId()) {
            return item1;
        } else if (itemId == item2.getItemId()) {
            return item2;
        } else if (itemId == item3.getItemId()) {
            return item3;
        } else {
            return null;
        }
    }

}
