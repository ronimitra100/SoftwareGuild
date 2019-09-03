/* Notes:
 * =======
 * 1) This program is a console game of Rock-Paper-Scissors between a user and the computer.
 * 2) The rules that need to be observed while playing this game can be found on
 *    https://lms.thesoftwareguild.com/courses/57/assignments/867?module_item_id=7183
 * 3) The numbers 1, 2, 3 and 0 indicate the choices of 'Rock', 'Paper', 'Scisscors' 
 *    and invalid choices respectively. 
 * 4) If a user enters some invalid input as their choice, they will lose in that round.
 * 5) When prompted whether the user wants to replay the game or not, any answer other than "Yes"
 *    will be contrued to mean "No".
 * 6) If the user enters some invalid answer to the question about how many rounds they want to play, 
 *    an error message will be displayed to them and they will be asked whther they want to play the game 
 *    again or not.
 */
package com.sg.basicprogrammingconcepts;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    public static int desiredNumOfRounds;
    public static final int MIN_NUM_OF_ROUNDS = 1;
    public static final int MAX_NUM_OF_ROUNDS = 10;
    public static final int ROCK = 1;
    public static final int PAPER = 2;
    public static final int SCISSORS = 3;
    public static int numOfWins;
    public static int numOfTies;
    public static int numOfLosses;

    public static void main(String[] args) {
        do {
            playGame();
        } while (wantToPlayGameAgain());
    }

    public static int getDesiredNumberOfRoundsFromUser() {
        int result = 0;
        Scanner inputReader = new Scanner(System.in);
        System.out.print("\nEnter the number of rounds you want to play. \n");
        int numOfRounds = Integer.parseInt(inputReader.nextLine());
        if (numOfRounds >= MIN_NUM_OF_ROUNDS && numOfRounds <= MAX_NUM_OF_ROUNDS) {
            result = numOfRounds;
        }
        return result;
    }

    public static int getUserChoice() {
        int result = 0;
        Scanner inputReader = new Scanner(System.in);
        System.out.print("Enter 1 for 'Rock', 2 for 'Paper' and 3 for 'Scissors'. \n");
        String userChoice = inputReader.nextLine();
        if (userChoice.equals("1") || userChoice.equals("2") || userChoice.equals("3")) {
            result = Integer.parseInt(userChoice);
        }
        return result;
    }

    public static int getComputerChoice() {
        Random randomizer = new Random();
        return randomizer.nextInt(3) + 1;
    }

    public static int getRoundOutcome(int userChoice, int computerChoice) {
        if (userChoice == computerChoice) {
            return 0;
        } else if ((userChoice == 2 & computerChoice == 1) || (userChoice == 3 & computerChoice == 2) || (userChoice == 1 & computerChoice == 3)) {
            return 1;
        } else {
            return -1;
        }
    }

    public static void declareGameResults(int wins, int losses, int ties) {
        System.out.println("----------------------------------------");
        System.out.println("GAME RESULTS");
        System.out.println("----------------------------------------");
        System.out.println("SCORE STATISTICS:");
        System.out.println("Total number of times you won: " + numOfWins);
        System.out.println("Total number of times computer won: " + numOfLosses);
        System.out.println("Total number of times there was a tie: " + numOfTies);

        System.out.println("\nFINAL GAME OUTCOME:");
        if (wins > losses) {
            System.out.println("Congrats, you won!");
        }

        if (wins < losses) {
            System.out.println("Sorry, you lost! Better luck next time.");
        }

        if (wins == losses) {
            System.out.println("It's a tie.");
        }
    }

    public static void playRound() {
        int userChoice = getUserChoice();
        int computerChoice = getComputerChoice();

        int roundOutcome = getRoundOutcome(userChoice, computerChoice);
        if (roundOutcome == 1) {
            numOfWins++;
            System.out.println("You won in this round: you had chosen " + userChoice + " and computer had chosen " + computerChoice);
        }
        if (roundOutcome == 0) {
            numOfTies++;
            System.out.println("This round was a tie: you had chosen " + userChoice + " and computer had chosen " + computerChoice);
        }
        if (roundOutcome == -1) {
            numOfLosses++;
            System.out.println("You lost in this round: you had chosen " + userChoice + " and computer had chosen " + computerChoice);
        }

    }

    public static void playGame() {
        desiredNumOfRounds = getDesiredNumberOfRoundsFromUser();

        if (desiredNumOfRounds >= 1) {
            for (int i = 0; i < desiredNumOfRounds; i++) {
                System.out.println("");
                playRound();
            }
        } else {
            System.out.println("You have entered an invalid answer.");
            return;
        }

        declareGameResults(numOfWins, numOfLosses, numOfTies);
        System.out.println("========================================");

    }

    public static boolean wantToPlayGameAgain() {
        boolean result;
        Scanner inputReader = new Scanner(System.in);
        numOfWins = 0;
        numOfLosses = 0;
        numOfTies = 0;
        System.out.print("\nDo you want to play game again? Print 'Yes' or 'No'\n");
        String userReply = inputReader.nextLine();
        if (userReply.equals("Yes")) {
            result = true;
        } else {
            result = false;
            System.out.println("\nThanks for playing!");
        }
        return result;
    }
}
