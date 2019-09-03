package com.sg.simplecalculator;

import java.util.Scanner;

public class SimpleCalculator {

    Scanner inputReader = new Scanner(System.in);

    int getOperationToBePerformedFromUser() {
        System.out.println("What Math operation do you want to perform. Enter 1 for Addition, 2 for Subtraction, 3 for Multiplication and 4 for Division. Enter 0 to exit.");
        return Integer.parseInt(inputReader.next());
    }

    float getFirstOperandFromUser() {
        System.out.print("Enter first operand: ");
        return Float.parseFloat(inputReader.next());
    }

    float getSecondOperandFromUser() {
        System.out.print("Enter second operand: ");
        return Float.parseFloat(inputReader.next());
    }

    float getSum(float a, float b) {
        return a + b;
    }

    float getDifference(float a, float b) {
        return (a - b);
    }

    float getProduct(float a, float b) {
        return (a * b);
    }

    float getRatio(float a, float b) {
        return (a / b);
    }

    void displayOperationResult(int operation, float operand1, float operand2) {
        float result = 0;
        switch (operation) {
            case 1:
                result = getSum(operand1, operand2);
                System.out.println("The sum of " + operand1 + " and " + operand2 + " is " + result);
                break;
            case 2:
                result = getDifference(operand1, operand2);
                System.out.println("The difference between " + operand1 + " and " + operand2 + " is " + result);
                break;
            case 3:
                result = getProduct(operand1, operand2);
                System.out.println("The product of " + operand1 + " and " + operand2 + " is " + result);
                break;
            case 4:
                if (operand2 != 0) {
                    result = getRatio(operand1, operand2);
                    System.out.println("The product of " + operand1 + " and " + operand2 + " is " + result);
                } else {
                    System.out.println("Division by 0 is not possible");
                }
                break;
            case 0:
                System.out.println("Thank you for using the calculator. See you again!");
                return;
            default:
                System.out.println("The chosen math operation is invalid. Please try again.");
        }
    }
}
