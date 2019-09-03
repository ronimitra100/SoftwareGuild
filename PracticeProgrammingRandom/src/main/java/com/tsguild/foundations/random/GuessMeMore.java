package com.tsguild.foundations.random;

import java.util.Random;
import java.util.Scanner;

public class GuessMeMore {

    public static void main(String[] args) {

        Random randomizer = new Random();
        Scanner myScanner = new Scanner(System.in);
        int programGuess = randomizer.nextInt(201) - 1;

        System.out.println("I've chosen a number between -100 and 100. Betcha can't guess it!");
        int userGuess = -1;
        do {
            System.out.print("Your guess: ");
            userGuess = Integer.parseInt(myScanner.next());

            if (userGuess == programGuess) {
                System.out.println("\n\nWow, nice guess! That was it!");
            } else if (userGuess < programGuess) {
                System.out.println("\n\nHa, nice try - too low! Try again!");
            } else if (userGuess > programGuess) {
                System.out.println("\n\nHa, nice try - too high! Try again!");
            } else {
                System.out.println("\n\nInvalid input");
            }

        } while (userGuess != programGuess);
    }
}




