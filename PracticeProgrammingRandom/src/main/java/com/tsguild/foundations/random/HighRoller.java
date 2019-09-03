package com.tsguild.foundations.random;
import java.util.Random;
import java.util.Scanner;

public class HighRoller {

    public static void main(String[] args) {

        Random diceRoller = new Random();
        Scanner myScanner = new Scanner(System.in);
        System.out.print("Enter the number of sides of the dice: ");
        int numOfDiceSides = Integer.parseInt(myScanner.nextLine());

        int rollResult = diceRoller.nextInt(numOfDiceSides) + 1;

        System.out.println("TIME TO ROOOOOOLL THE DICE!");
        System.out.println("I rolled a " + rollResult);

        if (rollResult == 1) {
            System.out.println("You rolled a critical failure!");
        }

        if (rollResult == 6) {
            System.out.println("You rolled a critical! Nice Job!");
        }
    }
}

