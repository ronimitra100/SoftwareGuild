package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.model.*;
import java.util.List;
import java.math.BigDecimal;

public interface VendingMachineServiceLayer {

    List<Item> getAllItems() throws VendingMachinePersistenceException;

    Item getItem(String itemId) throws VendingMachinePersistenceException;

    Item vendItem(String itemId, BigDecimal customerDeposit) throws VendingMachinePersistenceException, InsufficientFundsException, NoInventoryException, VendingMachineDataValidationException, InvalidItemSelectionException;

    String dispenseChange(BigDecimal customerDeposit, Item item, boolean isItemAvailable);

    public void setCurrentDeposit(BigDecimal deposit);

    public BigDecimal getCurrentDeposit();

    public void setItemSelection(String itemId);

    public String getItemSelection();

    public void setMessage(String message);

    public String getMessage();

    public void setChangeInfo(String changeInfo);

    public String getChangeInfo();

}
