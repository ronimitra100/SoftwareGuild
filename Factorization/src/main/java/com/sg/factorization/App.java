package com.sg.factorization;

public class App 
{
    public static void main( String[] args )
    {
        Factorizer factorizer = new Factorizer();
        int numberToBeFactored = factorizer.getDesiredNumberToBeFactored();
        factorizer.displayFactors(numberToBeFactored);
        factorizer.displayPrimeInfo(numberToBeFactored);
        factorizer.displayPerfectnessInfo(numberToBeFactored);
    }
}
