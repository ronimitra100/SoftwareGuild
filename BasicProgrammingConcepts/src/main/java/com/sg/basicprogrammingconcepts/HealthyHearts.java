package com.sg.basicprogrammingconcepts;

import java.util.Scanner;

public class HealthyHearts {

    public static void main(String[] args) {
        int age = getUserAge();
        System.out.println("Your maximum heart rate should be " + calculateMaxHeartRate(age) + " beats per minute");
        System.out.println("Your target HR Zone is " + getHRZone(age)[0] + " - " + getHRZone(age)[1] + " beats per minute");
    }

    public static int getUserAge() {
        Scanner inputReader = new Scanner(System.in);
        System.out.println("What is your age? ");
        int age = Integer.parseInt(inputReader.nextLine());
        return age;
    }

    public static int calculateMaxHeartRate(int age) {
        return (220 - age);
    }

    public static int[] getHRZone(int age) {
        int minHR = (int) Math.round(calculateMaxHeartRate(age) * 0.5);
        int maxHR = (int) Math.round(calculateMaxHeartRate(age) * 0.85);
        int[] result = {minHR, maxHR};
        return result;
    }
}
