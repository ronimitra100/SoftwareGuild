package com.tsguild.foundations.random;

import java.util.Random;
import java.util.Scanner;

public class DogGenetics {

    public static void main(String[] args) {

        Random randomizer = new Random();
        Scanner inputReader = new Scanner(System.in);

        int point1 = randomizer.nextInt(101);
        int point2 = randomizer.nextInt(101 - point1) + point1;
        int point3 = randomizer.nextInt(101 - point2) + point2;
        int point4 = randomizer.nextInt(101 - point3) + point3;

        System.out.print("What is your dog's name? ");
        String dogName = inputReader.nextLine();
        System.out.println("Well then, I have this highly reliable report on " + dogName + "\'s prestigious background right here.\n");
        System.out.println(dogName + " is:\n");

        System.out.println(point1 + "% St. Bernard");
        System.out.println((point2 - point1) + "% Chihuahua");
        System.out.println((point3 - point2) + "% Dramatic RedNosed Asian Pug");
        System.out.println((point4 - point3) + "% Common Cur");
        System.out.println((100 - point4) + "% King Doberman");

        System.out.println("\nWow, that's QUITE the dog!");
    }
}

