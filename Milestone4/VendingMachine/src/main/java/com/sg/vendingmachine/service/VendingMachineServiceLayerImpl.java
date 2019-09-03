package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.*;
import com.sg.vendingmachine.dto.*;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return dao.getAllItems();
    }

    @Override
    public Item getItem(String itemId) throws VendingMachinePersistenceException {
        return dao.getItem(itemId);
    }

    @Override
    public Item vendItem(String itemId, BigDecimal customerDeposit) throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException, VendingMachineDataValidationException {
        if (dao.getItem(itemId) == null) {
            throw new NoItemInventoryException("ERROR: " + itemId + " is not a valid Item ID.");
        } else if (!isItemDataValid(dao.getItem(itemId))) {
            throw new VendingMachineDataValidationException("ERROR: There is some data issue with the chosen item.");
        } else if (dao.getItem(itemId).getItemQuantity() <= 0) {
            throw new NoItemInventoryException("ERROR: The chosen item with Item ID " + itemId + " is out of stock.");
        } else if (dao.getItem(itemId).getItemCost().compareTo(customerDeposit) == 1) {
            throw new InsufficientFundsException("ERROR: The deposited amount $" + customerDeposit + " is not sufficient to buy " + dao.getItem(itemId).getItemName() +".");
        } else {
            return dao.vendItem(itemId);
        }
    }

    @Override
    public Change dispenseChange(BigDecimal customerDeposit, Item item, boolean isItemAvailable) {
        BigDecimal changeAmount = customerDeposit;
        if (item != null) {
            if (isItemAvailable && !(customerDeposit.compareTo(item.getItemCost()) == -1)) {
                changeAmount = customerDeposit.subtract(item.getItemCost());
            }

        }

        Change change = new Change(changeAmount);
        System.out.println(change.getChange(changeAmount));
        return change;
    }

    public boolean isItemDataValid(Item item) {

        return !(item.getItemName() == null
                || item.getItemName().trim().length() == 0
                || item.getItemCost() == null
                || !(item.getItemCost().toString().matches("[0-9]+([.][0-9]{1,2})?"))
                || Double.parseDouble(item.getItemCost().toString()) < 0
                || !(Integer.toString(item.getItemQuantity()).matches("[-+]?[0-9]+"))
                || item.getItemQuantity() < 0);

    }

}
