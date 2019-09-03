package com.sg.luckysevens;

import java.util.Random;
import java.util.Scanner;

public class LuckySevens {
    int playerBalance;
    int currentRollNo;
    int maxPlayerBalance;
    int rollNumLinkedToMaxPlayerBalance;

    int getBettingAmount() {
        System.out.print("How many dollars do you have? ");
        Scanner inputReader = new Scanner(System.in);
        return Integer.parseInt(inputReader.next());
    }

    void playGame(int bettingAmount) {
        maxPlayerBalance = bettingAmount;
        playerBalance = bettingAmount;
        currentRollNo = 1;
        while (playerBalance >= 1) {
            if (getDiceSumAfterRollingTwice() == 7) {
                playerBalance += 4;
                if (playerBalance > maxPlayerBalance) {
                    maxPlayerBalance = playerBalance;
                    rollNumLinkedToMaxPlayerBalance = currentRollNo;
                }
            } else {
                playerBalance -= 1;
            }
            currentRollNo++;
        }
        
        System.out.println("You are broke after " + currentRollNo + " rolls.");
        System.out.println("You should have quit after " + rollNumLinkedToMaxPlayerBalance + " rolls, when you had $" + maxPlayerBalance);
        
    }

    int getDiceSumAfterRollingTwice() {
        Random randomizer = new Random();
        int dice1 = randomizer.nextInt(6) + 1;
        int dice2 = randomizer.nextInt(6) + 1;
        return dice1 + dice2;
    }
}
