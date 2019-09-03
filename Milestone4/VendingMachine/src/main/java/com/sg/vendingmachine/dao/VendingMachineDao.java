package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.*;
import java.util.List;
import com.sg.vendingmachine.service.*;

public interface VendingMachineDao {

    List<Item> getAllItems() throws VendingMachinePersistenceException;

    Item getItem(String itemId) throws VendingMachinePersistenceException;

    Item vendItem(String itemId) throws VendingMachinePersistenceException;
}
