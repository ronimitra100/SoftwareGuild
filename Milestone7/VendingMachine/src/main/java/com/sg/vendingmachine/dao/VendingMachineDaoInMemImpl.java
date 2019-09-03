package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.model.*;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.*;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Scanner;
import java.math.BigDecimal;
import java.util.Collection;
import static org.hibernate.validator.internal.util.CollectionHelper.newHashMap;

public class VendingMachineDaoInMemImpl implements VendingMachineDao {
    private Map<String, Item> items = new HashMap();

    public VendingMachineDaoInMemImpl() {

        Item item = new Item("1", "Snickers", new BigDecimal("1.85"), 9);
        items.put(item.getItemId(), item);

        item = new Item("2", "M & Ms", new BigDecimal("1.50"), 2);
        items.put(item.getItemId(), item);

        item = new Item("3", "Pringles", new BigDecimal("2.10"), 5);
        items.put(item.getItemId(), item);

        item = new Item("4", "Reese's", new BigDecimal("1.85"), 4);
        items.put(item.getItemId(), item);

        item = new Item("5", "Pretzels", new BigDecimal("1.25"), 9);
        items.put(item.getItemId(), item);

        item = new Item("6", "Twinkies", new BigDecimal("1.95"), 3);
        items.put(item.getItemId(), item);

        item = new Item("7", "Doritos", new BigDecimal("1.75"), 11);
        items.put(item.getItemId(), item);

        item = new Item("8", "Almond Joy", new BigDecimal("1.85"), 0);
        items.put(item.getItemId(), item);

        item = new Item("9", "Trident", new BigDecimal("1.95"), 6);
        items.put(item.getItemId(), item);
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        Collection c = items.values();
        return new ArrayList<Item>(c);
    }

    @Override
    public Item getItem(String itemId) throws VendingMachinePersistenceException {
        return items.get(itemId);

    }

    @Override
    public Item updateItem(Item item) throws VendingMachinePersistenceException {
        items.put(item.getItemId(), item);
        return item;
    }

}
