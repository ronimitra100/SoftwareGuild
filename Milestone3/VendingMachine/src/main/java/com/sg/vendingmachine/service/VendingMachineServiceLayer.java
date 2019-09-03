package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.*;
import java.util.List;
import java.math.BigDecimal;

public interface VendingMachineServiceLayer {

    List<Item> getAllItems() throws VendingMachinePersistenceException;

    Item getItem(String itemId) throws VendingMachinePersistenceException;

    Item vendItem(String itemId, BigDecimal customerDeposit) throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException, VendingMachineDataValidationException;

    Change dispenseChange(BigDecimal customerDeposit, Item item, boolean isItemAvailable);

}
