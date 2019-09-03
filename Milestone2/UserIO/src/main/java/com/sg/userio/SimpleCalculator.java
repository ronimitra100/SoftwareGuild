package com.sg.userio;

public class SimpleCalculator {
    UserInputOutput io = new UserInputOutput();
    
    int getOperationToBePerformedFromUser() {
        return io.readInt("What Math operation do you want to perform. Enter 1 for Addition, 2 for Subtraction, 3 for Multiplication and 4 for Division. Enter 0 to exit.");
    }

    float getFirstOperandFromUser() {
        return io.readFloat("Enter first operand: ");
    }

    float getSecondOperandFromUser() {
        return io.readFloat("Enter second operand: ");
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
                io.print("The sum of " + operand1 + " and " + operand2 + " is " + result);
                break;
            case 2:
                result = getDifference(operand1, operand2);
                io.print("The difference between " + operand1 + " and " + operand2 + " is " + result);
                break;
            case 3:
                result = getProduct(operand1, operand2);
                io.print("The product of " + operand1 + " and " + operand2 + " is " + result);
                break;
            case 4:
                if (operand2 != 0) {
                    result = getRatio(operand1, operand2);
                    io.print("The product of " + operand1 + " and " + operand2 + " is " + result);
                } else {
                    io.print("Division by 0 is not possible");
                }
                break;
            case 0:
                io.print("Thank you for using the calculator. See you again!");
                return;
            default:
                io.print("The chosen math operation is invalid. Please try again.");
        }
    }
}

