package com.sg.interestcalculator;

public class App {

    public static void main(String[] args) {
        InterestCalculator interestCalculator = new InterestCalculator();
        interestCalculator.displayYearlyAccountSummary(10000, 4, 5, "quarterly");
    }
}
