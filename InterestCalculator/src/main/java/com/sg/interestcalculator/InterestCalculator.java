package com.sg.interestcalculator;

import java.util.Formatter;

public class InterestCalculator {

    double getCurrentBalance(double initialPrincipal, double annualInterestRate, int numOfYears, String compoundPeriod) {
        double currentBalance = initialPrincipal;
        boolean isInputDataValid = isUserInputValid(initialPrincipal, annualInterestRate, numOfYears, compoundPeriod);
        int factor = getMultiplicativeFactor(compoundPeriod);
        int numberOfInterestPeriods = numOfYears * factor;
        double interestPerCompoundPeriod = annualInterestRate / factor;

        if (isInputDataValid) {
            currentBalance = initialPrincipal;
            for (int i = 0; i < numberOfInterestPeriods; i++) {
                currentBalance *= (100 + interestPerCompoundPeriod) * 0.01;
            }
        }
        return currentBalance;
    }

    static boolean isUserInputValid(double initialPrincipal, double annualInterestRate, int numOfYears, String compoundPeriod) {
        boolean isValidCompoundInterest = (compoundPeriod == "quarterly" || compoundPeriod == "monthly" || compoundPeriod == "yearly");
        boolean isValidInitialPrincipal = (initialPrincipal > 0);
        boolean isValidAnnualInterestRate = (annualInterestRate > 0);
        boolean isValidNumOfYears = (numOfYears > 0);

        return (isValidCompoundInterest && isValidInitialPrincipal && isValidAnnualInterestRate && isValidNumOfYears);
    }

    static int getMultiplicativeFactor(String compoundPeriod) {
        int factor = 1;
        if (compoundPeriod.equals("quarterly")) {
            factor = 4;
        } else if (compoundPeriod.equals("monthly")) {
            factor = 12;
        } else if (compoundPeriod.equals("yearly")) {
            factor = 1;
        }
        return factor;
    }

    void displayYearlyAccountSummary(double initialPrincipal, double annualInterestRate, int numOfYears, String compoundPeriod) {
        System.out.println("Year     Starting Principal        Ending Principal         Interest");
        System.out.println("===      ===================       =================        =========");
        for (int year = 1; year <= numOfYears; year++) {
            double startingPrincipal = (double) Math.round(getCurrentBalance(initialPrincipal, annualInterestRate, year - 1, compoundPeriod) * 100 / 100);
            double endingPrincipal = (double) Math.round(getCurrentBalance(initialPrincipal, annualInterestRate, year, compoundPeriod) * 100 / 100);
            double annualInterestEarned = (double) Math.round((endingPrincipal - startingPrincipal) * 100 / 100);
            System.out.println(year + "         " + startingPrincipal + "                  " + endingPrincipal + "                   " + annualInterestEarned);
        }
    }
}
