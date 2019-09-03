package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.logging.Logger;

public class Change {

    public int numOfQuarters;
    public int numOfDimes;
    public int numOfNickels;
    public int numOfPennies;

    public Change(BigDecimal changeAmountInDollars) {
        int amountOfChangeInCents = getAmountInCents(changeAmountInDollars);
        numOfQuarters = getNumOfQuarters(amountOfChangeInCents);
        numOfDimes = getNumOfDimes(amountOfChangeInCents);
        numOfNickels = getNumOfNickels(amountOfChangeInCents);
        numOfPennies = getNumOfPennies(amountOfChangeInCents);
    }

    public int getNumOfQuarters(int amountOfChangeInCents) {
        return (amountOfChangeInCents / 25);
    }

    public int getNumOfDimes(int amountOfChangeInCents) {
        return ((amountOfChangeInCents % 25) / 10);
    }

    public int getNumOfNickels(int amountOfChangeInCents) {
        return ((((amountOfChangeInCents) % 25) % 10) / 5);
    }

    public int getNumOfPennies(int changeAmountInCents) {
        return ((((changeAmountInCents) % 25) % 10) % 5);
    }

    public String getChange(BigDecimal changeAmountInDollars) {
        Change change = new Change(changeAmountInDollars);
        String message = "The Change is : ";

        if (numOfQuarters > 0) {
            message += numOfQuarters + " quarters";
        }

        if (numOfDimes > 0) {
            if (numOfQuarters > 0) {
                message += ", ";
            }
            message += numOfDimes + " dimes";
        }

        if (numOfNickels > 0) {
            if (numOfQuarters > 0 || numOfDimes > 0) {
                message += ", ";
            }
            message += numOfNickels + " nickels";
        }

        if (numOfPennies > 0) {
            if (numOfQuarters > 0 || numOfDimes > 0 || numOfPennies > 0) {
                message += ", ";
            }
            message += numOfPennies + " pennies";

        }

        if (numOfQuarters == 0 && numOfDimes == 0 && numOfNickels == 0 && numOfPennies == 0) {
            message += "0.00";
        }
        message += ".";
        return message;
    }

    public int getAmountInCents(BigDecimal amountInDollars) {
        Double amountInCentsAsDouble = amountInDollars.multiply(new BigDecimal("100.00")).doubleValue();
        Integer changeAmountInCents = amountInCentsAsDouble.intValue();
        return changeAmountInCents;
    }

}
