package com.sg.arrays;
import java.util.Random;

public class HiddenNuts {
    public static void main(String[] args){
        String[] hidingSpots = new String[100];
        Random squirrel = new Random();
        hidingSpots[squirrel.nextInt(hidingSpots.length)]="Nuts";
        System.out.println("The nut has been hidden...");
        
        for (int i=0; i<hidingSpots.length; i++){
            if (hidingSpots[i]=="Nuts"){
                System.out.println("The nut was hidden at  the" + i +"th spot");
            }
        }
    }
}
