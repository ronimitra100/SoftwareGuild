package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.*;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Scanner;
import java.math.BigDecimal;

public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private Map<String, Item> items = new HashMap<String, Item>();
    public static final String INVENTORY_FILE = "inventory.txt";
    public static final String DELIMITER = "::";

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        readInventory();
        return new ArrayList<Item>(items.values());
    }

    @Override
    public Item getItem(String itemId) throws VendingMachinePersistenceException {
        readInventory();
        return items.get(itemId);

    }

    @Override
    public Item vendItem(String itemId) throws VendingMachinePersistenceException {
        Item vendedItem = this.getItem(itemId);
        int currenItemQuantity = vendedItem.getItemQuantity();
        vendedItem.setItemQuantity(currenItemQuantity - 1);
        items.put(itemId, vendedItem);
        writeInventory();
        return vendedItem;
    }

    public void readInventory() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(INVENTORY_FILE)));

        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("-_- Could not load inventory data into memory", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Item currentItem = new Item(currentTokens[0]);
            currentItem.setItemName(currentTokens[1]);
            currentItem.setItemCost(new BigDecimal(currentTokens[2]));
            currentItem.setItemQuantity(Integer.parseInt(currentTokens[3]));

            items.put(currentItem.getItemId(), currentItem);
        }
        scanner.close();
    }

    public void writeInventory() throws VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE), true);
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not save file, e");
        }

        for (Item currentItem : items.values()) {
            out.println(currentItem.getItemId() + DELIMITER
                    + currentItem.getItemName() + DELIMITER
                    + currentItem.getItemCost() + DELIMITER
                    + currentItem.getItemQuantity());
            out.flush();
        }
        out.close();
    }

}
