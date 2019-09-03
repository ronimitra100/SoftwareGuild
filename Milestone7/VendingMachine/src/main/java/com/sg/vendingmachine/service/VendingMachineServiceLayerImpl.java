package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.*;
import com.sg.vendingmachine.model.*;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.inject.Inject;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    VendingMachineDao dao;
    BigDecimal currentDeposit = new BigDecimal("0.00");
    String itemSelection;
    String message;
    String changeInfo;
    Change change;

    @Inject
    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
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
    public Item vendItem(String itemId, BigDecimal customerDeposit) throws VendingMachinePersistenceException, InsufficientFundsException, NoInventoryException, VendingMachineDataValidationException, InvalidItemSelectionException {
        if (itemId == null || itemId == "") {
            throw new InvalidItemSelectionException("Please choose the item you wish to purchase!");
        }
        if (dao.getItem(itemId) == null) {
            throw new InvalidItemSelectionException("Entered Item ID " + itemId + " is not valid.");
        }
        if (!isItemDataValid(dao.getItem(itemId))) {
            throw new VendingMachineDataValidationException("There is some data issue with the chosen item");
        }
        if (dao.getItem(itemId).getItemQuantity() <= 0) {
            throw new NoInventoryException("SOLD OUT!!!");
        }
        if (dao.getItem(itemId).getItemCost().compareTo(customerDeposit) == 1) {
            throw new InsufficientFundsException("Please deposit: $" + dao.getItem(itemId).getItemCost().subtract(customerDeposit) +".");
        }

        Item vendedItem = dao.getItem(itemId);
        int currenItemQuantity = vendedItem.getItemQuantity();
        vendedItem.setItemQuantity(currenItemQuantity - 1);
        return dao.updateItem(vendedItem);

    }

    @Override
    public String dispenseChange(BigDecimal customerDeposit, Item item, boolean isItemAvailable) {
        BigDecimal changeAmount = customerDeposit;
        if (item != null) {
            if (isItemAvailable && !(customerDeposit.compareTo(item.getItemCost()) == -1)) {
                changeAmount = customerDeposit.subtract(item.getItemCost());
            }

        }

        this.change = new Change(changeAmount);
        this.changeInfo = change.getChange(changeAmount);
        System.out.println(change.getChange(changeAmount));
        return changeInfo;
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

    @Override
    public void setCurrentDeposit(BigDecimal deposit) {
        BigDecimal priorDeposit = this.currentDeposit;
        this.currentDeposit = priorDeposit.add(deposit);
    }

    @Override
    public BigDecimal getCurrentDeposit() {
        return this.currentDeposit;
    }

    @Override
    public void setItemSelection(String itemId) {
        this.itemSelection = itemId;
    }

    @Override
    public String getItemSelection() {
        return this.itemSelection;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public void setChangeInfo(String changeInfo) {
        this.changeInfo = changeInfo;
    }

    @Override
    public String getChangeInfo() {
        return this.changeInfo;
    }

}
