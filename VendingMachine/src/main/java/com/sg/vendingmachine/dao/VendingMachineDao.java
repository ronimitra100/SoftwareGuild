package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.model.Item;
import java.util.List;
import com.sg.vendingmachine.service.*;

public interface VendingMachineDao {

    List<Item> getAllItems() throws VendingMachinePersistenceException;

    Item getItem(String itemId) throws VendingMachinePersistenceException;

    Item updateItem(Item item) throws VendingMachinePersistenceException;

}
