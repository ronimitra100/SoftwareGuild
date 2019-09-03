package com.sg.factorization;

import java.util.Scanner;

public class Factorizer {

    public int getDesiredNumberToBeFactored() {
        int result = 1;
        Scanner inputReader = new Scanner(System.in);
        System.out.println("Enter the number that you want to be factorized: ");
        int userInput = Integer.parseInt(inputReader.next());
        if (userInput > 1) {
            result = userInput;
        }
        System.out.println("\nYou chose to factorize " + result + ".");
        return result;
    }

    public int getTotalNumberOfFactors(int numberToBeFactored) {
        int count = 0;
        if (numberToBeFactored > 1) {
            for (int i = 1; i <= numberToBeFactored; i++) {
                if (numberToBeFactored % i == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public void displayNumberOfFactors(int numberToBeFactored) {
        System.out.println("The chosen number " + numberToBeFactored + " has " + getTotalNumberOfFactors(numberToBeFactored) + " factors.");
    }

    public int[] getFactors(int numberToBeFactored) {
        int numberOfFactors = getTotalNumberOfFactors(numberToBeFactored);
        if (numberOfFactors == 1) {
            System.out.println("The chosen number is 1 and has no factors other than itself");
            return null;
        } else {
            int[] result = new int[numberOfFactors - 1];
            int index = 0;
            for (int i = 1; i < numberToBeFactored; i++) {
                if (numberToBeFactored % i == 0) {
                    result[index] = i;
                    index++;
                }
            }
            return result;
        }
    }

    public void displayFactors(int numberToBeFactored) {
        int[] factors = getFactors(numberToBeFactored);
        System.out.println("\nFollowing are the factors of " + numberToBeFactored + ":");
        for (int j = 0; j < factors.length; j++) {
            System.out.print(factors[j] + " ");
        }
        System.out.println("\n");
    }

    public boolean isPrime(int numberToBeFactored) {
        return (getTotalNumberOfFactors(numberToBeFactored) == 2);
    }

    public boolean isPerfect(int numberToBeFactored) {
        boolean result = false;

        if (numberToBeFactored > 1) {
            int[] factors = getFactors(numberToBeFactored);
            int sumOfFactors = 0;
            for (int i = 0; i < factors.length; i++) {
                sumOfFactors += factors[i];
            }
            result = (sumOfFactors == numberToBeFactored);
        }
        return result;
    }

    public void displayPrimeInfo(int numberToBeFactored) {
        if (isPrime(numberToBeFactored)) {
            System.out.println(numberToBeFactored + " is a prime.");
        } else {
            System.out.println(numberToBeFactored + " is not a prime.");
        }
    }

    public void displayPerfectnessInfo(int numberToBeFactored) {
        if (isPerfect(numberToBeFactored)) {
            System.out.println(numberToBeFactored + " is perfect.");
        } else {
            System.out.print(numberToBeFactored + " is not perfect.");
        }
    }
}
