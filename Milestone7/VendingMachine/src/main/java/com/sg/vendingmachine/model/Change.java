package com.sg.vendingmachine.model;

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

        String message = "";

        if (numOfQuarters > 0) {
            if (numOfQuarters == 1) {
                message += "1 Quarter";
            } else {
                message += numOfQuarters + " Quarters";
            }
        }

        if (numOfDimes > 0) {
            if (numOfQuarters > 0) {
                message += ", ";
            }
            if (numOfDimes == 1) {
                message += "1 Dime";
            } else {
                message += numOfDimes + " Dimes";
            }
        }

        if (numOfNickels > 0) {
            if (numOfQuarters > 0 || numOfDimes > 0) {
                message += ", ";
            }
            if (numOfNickels == 1) {
                message += "1 Nickel";
            } else {
                message += numOfNickels + " Nickels";
            }

        }

        if (numOfPennies > 0) {
            if (numOfQuarters > 0 || numOfDimes > 0 || numOfPennies > 0) {
                message += ", ";
            }
            if (numOfPennies == 1) {
                message += "1 Penny";
            } else {
                message += numOfPennies + " Pennies";
            }

        }

        if (numOfQuarters == 0 && numOfDimes == 0 && numOfNickels == 0 && numOfPennies == 0) {
            message += "No Change";
        }
        message += ".";
        return message;
    }

    public int getAmountInCents(BigDecimal amountInDollars) {
        Double amountInCentsAsDouble = amountInDollars.multiply(new BigDecimal("100.00")).doubleValue();
        Integer changeAmountInCents = amountInCentsAsDouble.intValue();
        return changeAmountInCents;
    }

    public String getChangeInformation(Change change) {
        String message = "";
        numOfQuarters = this.numOfQuarters;
        numOfDimes = this.numOfDimes;
        numOfNickels = this.numOfNickels;
        numOfPennies = this.numOfPennies;
        if (numOfQuarters > 0) {
            if (numOfQuarters == 1) {
                message += "1 Quarter";
            } else {
                message += numOfQuarters + " Quarters";
            }

        }

        if (numOfDimes > 0) {
            if (numOfQuarters > 0) {
                message += ", ";
            }
            if (numOfDimes == 1) {
                message += "1 Dime";
            } else {
                message += numOfDimes + " Dimes";
            }
        }

        if (numOfNickels > 0) {
            if (numOfQuarters > 0 || numOfDimes > 0) {
                message += ", ";
            }
            if (numOfNickels == 1) {
                message += "1 Nickel";
            } else {
                message += numOfNickels + " Nickels";
            }

        }
        return message;
    }

}
