package com.swgcorp.flooringmastery.ui;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserIOConsoleImpl implements UserIO {

    @Override
    public void print(String message) {
        System.out.println("\n" + message);
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    public String readString(String prompt) {
        print(prompt);
        Scanner inputReader = new Scanner(System.in);
        String userInput = inputReader.nextLine();
        return userInput;
    }

    @Override
    public BigDecimal readPositiveNumberWithTwoDecimalPlaces(String prompt) {
        print(prompt);
        Scanner inputReader = new Scanner(System.in);
        String userInput = inputReader.next();

        if (userInput==null || userInput.trim().length()==0||!isStringAPositiveDecimalWithTwoDigits(userInput)) {
            System.out.println("Entered value is not valid. Please enter a positive number with two places of decimals.");
            return readPositiveNumberWithTwoDecimalPlaces(prompt);
        } else if (Double.parseDouble(userInput) < 0) {
            System.out.println("Negative values are not allowed. Please enter a positive number with two places of decimals.");
            return readPositiveNumberWithTwoDecimalPlaces(prompt);
        } else {
            return new BigDecimal(userInput);
        }
    }

    @Override
    public LocalDate readDate(String prompt, DateTimeFormatter dateFormat) {
        print(prompt);
        Scanner inputReader = new Scanner(System.in);
        String userInput = inputReader.next();

        return LocalDate.parse(userInput, dateFormat);
    }

    public boolean isStringIntegral(String str) {
        return str.matches("[-+]?[0-9]+");
    }

    public boolean isStringADecimal(String str) {
        return (str.matches("[-+]?([0-9]*\\.[0-9]+|[0-9]+)"));
    }

    public boolean isStringAPositiveDecimalWithTwoDigits(String str) {
        return (str.matches("[0-9]+([.][0-9]{1,2})?"));
    }

    public boolean isStringPositiveIntegral(String str) {
        return str.matches("[0-9]+");
    }

    /*public boolean isDateValid(String dateToCheck, String datePattern){
        boolean result = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        try{
            LocalDate localDate = LocalDate.parse(dateToCheck, formatter);
            result = true;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
        return result;
    }*/
}
