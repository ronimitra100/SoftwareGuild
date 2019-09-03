package com.sg.vendingmachine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sg.vendingmachine.dao.*;
import com.sg.vendingmachine.service.*;
import com.sg.vendingmachine.model.*;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VendingMachineController {

    VendingMachineDao dao;
    VendingMachineServiceLayer service;
    BigDecimal currentDeposit;
    String itemSelection;
    String message = "";
    String changeInfo = "";

    @Inject
    public VendingMachineController(VendingMachineServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayVendingMachineLandingPage(Model model) throws VendingMachinePersistenceException {
        List<Item> itemList = service.getAllItems();

        currentDeposit = service.getCurrentDeposit();
        itemSelection = service.getItemSelection();
        message = service.getMessage();
        changeInfo = service.getChangeInfo();

        model.addAttribute("itemList", itemList);
        if (currentDeposit.compareTo(new BigDecimal("0.00")) != 0) {
            model.addAttribute("currentDeposit", currentDeposit);
        }
        model.addAttribute("itemSelection", itemSelection);
        model.addAttribute("message", message);
        if (message == "Thanks!!!") {
            model.addAttribute("changeInfo", changeInfo);
        }

        return "index";
    }

    @RequestMapping(value = "/addMoney", method = RequestMethod.POST)
    public String addMoney(HttpServletRequest request, Model model) {
        String buttonLabel = request.getParameter("money");
        currentDeposit = new BigDecimal("0.00");

        if (buttonLabel.equals("Add Dollar")) {
            service.setCurrentDeposit(new BigDecimal("1.00"));
        }

        if (buttonLabel.equals("Add Quarter")) {
            service.setCurrentDeposit(new BigDecimal("0.25"));
        }

        if (buttonLabel.equals("Add Dime")) {
            service.setCurrentDeposit(new BigDecimal("0.10"));
        }

        if (buttonLabel.equals("Add Nickel")) {
            service.setCurrentDeposit(new BigDecimal("0.05"));
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/selectItem", method = RequestMethod.GET)
    public String selectItem(HttpServletRequest request) {
        String idOfSelectedItem = request.getParameter("id");
        service.setItemSelection(idOfSelectedItem);

        return "redirect:/";
    }

    @RequestMapping(value = "/makePurchase", method = RequestMethod.POST)
    public String makePurchase(HttpServletRequest request) throws VendingMachinePersistenceException, InsufficientFundsException, NoInventoryException, VendingMachineDataValidationException, InvalidItemSelectionException {
        String itemId = service.getItemSelection();
        if (itemId == null) {

        }
        BigDecimal customerDeposit = service.getCurrentDeposit();
        try {
            service.vendItem(itemId, customerDeposit);
            service.setMessage("Thanks!!!");
            service.dispenseChange(customerDeposit, service.getItem(itemId), true);
        } catch (InvalidItemSelectionException e) {
            service.setMessage(e.getMessage());
            service.dispenseChange(customerDeposit, null, false);
        } catch (VendingMachineDataValidationException e) {
            service.setMessage(e.getMessage());
            service.dispenseChange(customerDeposit, null, true);
        } catch (NoInventoryException e) {
            service.setMessage(e.getMessage());
            service.dispenseChange(customerDeposit, null, false);
        } catch (InsufficientFundsException e) {
            service.setMessage(e.getMessage());
            service.dispenseChange(customerDeposit, service.getItem(itemId), true);
        } catch (VendingMachinePersistenceException e) {
            service.setMessage(e.getMessage());
            service.dispenseChange(customerDeposit, service.getItem(itemId), false);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/getChange", method = RequestMethod.GET)
    public String getChange(Model model) {
        service.setCurrentDeposit(service.getCurrentDeposit().negate());
        service.setItemSelection("");
        service.setMessage("");
        service.setChangeInfo("");
        return "redirect:/";
    }

}
