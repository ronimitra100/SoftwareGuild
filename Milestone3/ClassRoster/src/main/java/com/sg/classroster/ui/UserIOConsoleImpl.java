package com.sg.classroster.ui;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class UserIOConsoleImpl implements UserIO {

    public void print(String message) {
        System.out.println("\n" + message);
    }

    public double readDouble(String prompt) {
        print(prompt);
        Scanner inputReader = new Scanner(System.in);
        String userInput = inputReader.next();
        if (!isStringADecimal(userInput)) {
            System.out.println("Entered input is not numeric, please try again.");
            return readDouble(prompt);
        } else {
            return Double.parseDouble(userInput);
        }
    }

    public double readDouble(String prompt, double min, double max) {
        print(prompt);
        Scanner inputReader = new Scanner(System.in);
        String userInput = inputReader.next();
        if (!isStringADecimal(userInput)) {
            System.out.println("Entered input is not numeric, please try again.");
            return readDouble(prompt, min, max);
        } else if (Double.parseDouble(userInput) < min || Double.parseDouble(userInput) > max) {
            System.out.println("Entered input is not in the correct range: " + min + "-" + max + " , please try again.");
            return readDouble(prompt, min, max);
        } else {
            return Double.parseDouble(userInput);
        }
    }

    public float readFloat(String prompt) {
        print(prompt);
        Scanner inputReader = new Scanner(System.in);
        String userInput = inputReader.next();
        if (!isStringADecimal(userInput)) {
            System.out.println("Entered input is not numeric, please try again.");
            return readFloat(prompt);
        } else {
            return Float.parseFloat(userInput);
        }
    }

    public float readFloat(String prompt, float min, float max) {
        print(prompt);
        Scanner inputReader = new Scanner(System.in);
        String userInput = inputReader.next();
        if (!isStringADecimal(userInput)) {
            System.out.println("Entered input is not numeric, please try again.");
            return readFloat(prompt, min, max);
        } else if (Float.parseFloat(userInput) < min || Float.parseFloat(userInput) > max) {
            System.out.println("Entered input is not in the correct range: " + min + "-" + max + " , please try again.");
            return readFloat(prompt, min, max);
        } else {
            return Float.parseFloat(userInput);
        }
    }

    public int readInt(String prompt) {
        print(prompt);
        Scanner inputReader = new Scanner(System.in);
        String userInput = inputReader.next();
        if (!isStringIntegral(userInput)) {
            System.out.println("Entered input is not numeric, please try again.");
            return readInt(prompt);
        } else {
            return Integer.parseInt(userInput);
        }
    }

    public int readInt(String prompt, int min, int max) {
        print(prompt);
        Scanner inputReader = new Scanner(System.in);
        String userInput = inputReader.next();
        if (!isStringIntegral(userInput)) {
            System.out.println("Entered input is not an integral, please try again.");
            return readInt(prompt, min, max);
        } else if (Integer.parseInt(userInput) < min || Integer.parseInt(userInput) > max) {
            System.out.println("Entered input is not in the correct range: " + min + "-" + max + " , please try again.");
            return readInt(prompt, min, max);
        } else {
            return Integer.parseInt(userInput);
        }
    }

    public long readLong(String prompt) {
        print(prompt);
        Scanner inputReader = new Scanner(System.in);
        String userInput = inputReader.next();
        if (!isStringADecimal(userInput)) {
            System.out.println("Entered input is not numeric, please try again.");
            return readLong(prompt);
        } else {
            return Long.parseLong(userInput);
        }
    }

    public long readLong(String prompt, long min, long max) {
        print(prompt);
        Scanner inputReader = new Scanner(System.in);
        String userInput = inputReader.next();
        if (!isStringADecimal(userInput)) {
            System.out.println("Entered input is not numeric, please try again.");
            return readLong(prompt, min, max);
        } else if (Long.parseLong(userInput) < min || Long.parseLong(userInput) > max) {
            System.out.println("Entered input is not in the correct range: " + min + "-" + max + " , please try again.");
            return readLong(prompt, min, max);
        } else {
            return Long.parseLong(userInput);
        }
    }

    public String readString(String prompt) {
        print(prompt);
        Scanner inputReader = new Scanner(System.in);
        String userInput = inputReader.nextLine();
        return userInput;
    }

    public boolean isStringIntegral(String str) {
        return str.matches("[-+]?[0-9]+");
    }

    public boolean isStringADecimal(String str) {
        return (str.matches("[-+]?([0-9]*\\.[0-9]+|[0-9]+)"));
    }
}
